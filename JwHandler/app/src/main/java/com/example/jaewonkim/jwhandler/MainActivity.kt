package com.example.jaewonkim.jwhandler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var thread = Thread {
            run {
                Thread.sleep(3000)
                Handler(Looper.getMainLooper()).post {
                    textView_main.setText("쓰레드 작동")
                }
            }
        }
        thread.start()
    }
}
