package com.gandan.android.thingsflowtest

import android.app.Application
import com.gandan.android.thingsflowtest.di.AppComponent
import com.gandan.android.thingsflowtest.di.DaggerAppComponent

class BaseApplication: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}