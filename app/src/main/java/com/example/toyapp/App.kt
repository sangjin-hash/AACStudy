package com.example.toyapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){

    override fun onCreate(){
        super.onCreate()
    }
/*init {
        instance = this
    }

    companion object{
        var instance: App? = null
        fun context(): Context {                                            //사용방법 : App.context() -> context가 return된다.
            return instance!!.applicationContext
        }
    }*/
}