package com.example.ktorsample.di.repository

import com.example.ktorsample.data.model.PostApi
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

	fun getPosts(): Flow<List<PostApi>>

	fun postNum(num:Int): Flow<Int>
}