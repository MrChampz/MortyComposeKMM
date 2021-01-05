package com.upco.playground.mortycomposekmm.android

import android.app.Application
import com.upco.playground.mortycomposekmm.android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MortyComposeKMMApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MortyComposeKMMApplication)
            modules(appModule)
        }
    }
}