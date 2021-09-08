package kr.uni.libraryreview.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("itemId") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val desc: String,
    @SerializedName("coverSmallUrl") val coverSmallUrl: String
)
