package com.example.jaewonkim.jwbroadcast

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var myBroadCastReceiver = MyBroadCastReceiver()
    var intentFilter = IntentFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        //배터리 부족
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
        registerReceiver(myBroadCastReceiver, intentFilter)
    }
}
