package com.abs.clase14

import android.app.Application
import com.abs.clase14.modules.appModule
import com.abs.clase14.modules.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GifApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@GifApplication)
            modules(listOf(appModule, networkModule))
        }
    }
}