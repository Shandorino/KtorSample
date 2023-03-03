package com.example.ktorsample.di.repository.impl

import com.example.ktorsample.data.api.ApiService
import com.example.ktorsample.data.model.PostApi
import com.example.ktorsample.di.repository.ApiRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ApiRepositoryImpl(
	private val apiService: ApiService
): ApiRepository {

	override fun getPosts(): Flow<List<PostApi>> = flow {
		val result = apiService.getPosts()
		emit(result)
	}

	override fun postNum(num: Int): Flow<Int> = flow{
		apiService.postNum(num)
		emit(1)
	}
}