package com.gandan.android.thingsflowtest

import android.app.Application

class BaseApplication: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}