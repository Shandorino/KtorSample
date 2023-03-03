package com.example.ktorsample.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktorsample.data.model.PostApi
import com.example.ktorsample.di.repository.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch

class MainViewModel(
	private val apiRepository: ApiRepository
): ViewModel() {

	val posts = MutableStateFlow<List<PostApi>>(listOf())

	fun getPosts() {

		viewModelScope.launch {

			apiRepository.getPosts().collect {
				posts.emit(it)
			}

		}

	}

	fun clear() {

		viewModelScope.launch {
			posts.emit(listOf())
		}

	}

	fun sendNum(num: Int) {
		apiRepository.postNum(num)
	}


}