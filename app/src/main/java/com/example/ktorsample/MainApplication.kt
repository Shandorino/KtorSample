package com.example.ktorsample

import android.app.Application
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ktorsample.di.AppModule
import com.example.ktorsample.view_model.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {

			androidContext(this@MainApplication)

			modules(AppModule)

		}


	}


}