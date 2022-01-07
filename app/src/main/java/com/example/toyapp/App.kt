package com.example.toyapp

import android.app.Application
import android.content.Context

// Room DB 생성때 context 때문에 전역적으로 사용가능한 context를 생성하는 class -> Manifest에 <application> android:name=".App" 추가해줘야 한다.
class App : Application(){
    init {
        instance = this
    }

    companion object{
        var instance: App? = null
        fun context(): Context {                                            //사용방법 : App.context() -> context가 return된다.
            return instance!!.applicationContext
        }
    }
}