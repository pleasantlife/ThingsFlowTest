package com.gandan.android.thingsflowtest.di

import android.content.Context
import com.gandan.android.thingsflowtest.view.DetailActivity
import com.gandan.android.thingsflowtest.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
}