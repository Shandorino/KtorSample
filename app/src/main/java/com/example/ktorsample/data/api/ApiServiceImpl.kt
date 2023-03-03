package com.example.ktorsample.data.api

import android.util.Log
import com.example.ktorsample.data.model.PostApi
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import java.net.ProtocolFamily

class ApiServiceImpl(
	override val httpClient: HttpClient
): ApiService {

	override suspend fun getPosts(): List<PostApi> {

		val gson = Gson()

		val response = httpClient.get(urlString = "https://jsonplaceholder.typicode.com/posts")

		return gson.fromJson(response.bodyAsText(), Array<PostApi>::class.java).toList()

	}


	override suspend fun postNum(num: Int) {

		httpClient.post{
			url {
				protocol = URLProtocol.HTTP
				host = "10.10.1.6"
				port = 25562
			}

		}



	}
}