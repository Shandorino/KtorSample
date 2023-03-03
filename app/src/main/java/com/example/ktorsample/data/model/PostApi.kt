package com.example.ktorsample.data.model


import com.google.gson.annotations.SerializedName

data class PostApi(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)