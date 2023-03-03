package com.example.ktorsample.di

import com.example.ktorsample.data.api.ApiService
import com.example.ktorsample.data.api.ApiServiceImpl
import com.example.ktorsample.di.repository.ApiRepository
import com.example.ktorsample.di.repository.impl.ApiRepositoryImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import org.koin.dsl.module


val ApiModule = module {


	single<ApiService> {
		ApiServiceImpl(get())
	}

	single<ApiRepository> {
		ApiRepositoryImpl(get())
	}

	single {
		HttpClient(Android)
	}

}