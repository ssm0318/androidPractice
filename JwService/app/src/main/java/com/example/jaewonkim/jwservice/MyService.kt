package com.example.jaewonkim.jwservice

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {
    //서비스가 생성될때 작동
    override fun onCreate() {
        super.onCreate()
    }

    //생성되기 직전에 작동
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            run {
                while (true) {
                    Thread.sleep(3000)
                    //logcat보면 앱 꺼도 이 메세지 계속 보내지는 것 확인할 수 있음.
                    println("서비스 실행 중입니다.")
                }
            }
        }.start()

        //서비스가 비정상적으로 종료되어도 끈질기게 다시 시작하게 해주는 것.
        return START_STICKY
    }

    //서비스가 제거 될 때 작동
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}