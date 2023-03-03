package com.example.ktorsample.di

import com.example.ktorsample.view_model.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

	includes(ApiModule)


	viewModel {

		MainViewModel(get())

	}

}