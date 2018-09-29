package com.example.jaewonkim.jwservicebind

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyService : Service() {
    var message : String? = null

    override fun onCreate() {
        super.onCreate()
        message = "서비스가 작동 중입니다."
    }

    inner class MyBinder : Binder() {
        fun getService() : MyService {
            //getService 불려지면 MyService 돌려줌
            return this@MyService
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return MyBinder()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}