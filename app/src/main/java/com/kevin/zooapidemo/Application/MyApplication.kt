package com.kevin.zooapidemo.Application

import android.app.Application
import android.content.Context
import com.kevin.zooapidemo.Network.HttpApiClient

class MyApplication : Application() {


    init { instance = this }
    companion object {
        private lateinit var instance: MyApplication
        fun getInstance() = instance
        fun getAppContext() : Context { return instance.applicationContext }
    }

    override fun onCreate() {
        super.onCreate()
        HttpApiClient.initialize()

    }

}