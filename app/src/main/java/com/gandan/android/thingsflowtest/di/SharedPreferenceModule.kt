package com.gandan.android.thingsflowtest.di

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferenceModule {

    @Singleton
    @Provides
    fun getSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences("things_flow", Activity.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun getSharedPreferenceEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }
}