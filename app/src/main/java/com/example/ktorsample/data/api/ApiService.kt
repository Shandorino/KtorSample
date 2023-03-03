package com.example.ktorsample.data.api

import com.example.ktorsample.data.model.PostApi
import io.ktor.client.*

interface ApiService {

	val httpClient: HttpClient

	suspend fun getPosts(): List<PostApi>

	suspend fun postNum(num: Int)

}