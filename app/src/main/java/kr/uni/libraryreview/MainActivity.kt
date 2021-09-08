package kr.uni.libraryreview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kr.uni.libraryreview.adapter.BookAdapter
import kr.uni.libraryreview.api.BookService
import kr.uni.libraryreview.databinding.ActivityMainBinding
import kr.uni.libraryreview.model.BestSellerDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBookRecyclerView()
        setAdapterData()


    }

    private fun initBookRecyclerView() {
        adapter = BookAdapter()
        binding.bookRecyclerView.adapter = adapter
        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

    }

    private fun setAdapterData() {
        val retrofit = Retrofit.Builder().apply {
            baseUrl(Constants.url)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
        val bookService = retrofit.create(BookService::class.java)

        val req = bookService.getBestSeller()
        req.enqueue(object : Callback<BestSellerDto> {
            override fun onResponse(call: Call<BestSellerDto>, response: Response<BestSellerDto>) {
                //todo 성공처리
                if (response.isSuccessful.not()) {
                    return
                }
                response.body()?.let {
                    adapter.submitList(it.item)
                }
            }

            override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                //todo 실패처리
                Log.e(TAG, "$t")
            }
        })

    }


}