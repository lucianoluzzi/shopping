package com.lucianoluzzi.shopping

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ShoppingApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ShoppingApplication)
            modules(
                appModule
            )
        }
    }
}