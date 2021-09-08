package kr.uni.libraryreview.api

import kr.uni.libraryreview.Constants
import kr.uni.libraryreview.model.BestSellerDto
import kr.uni.libraryreview.model.SearchBookDto
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("/api/search.api?output=json")
    fun getBooksByName(
        @Query("key") key: String = Constants.key,
        @Query("query") keyword: String = "안드로이드"
    ): Call<SearchBookDto>

    @GET("/api/bestSeller.api?output=json&categoryId=100")
    fun getBestSeller(
        @Query("key") key: String = Constants.key
    ): Call<BestSellerDto>
}