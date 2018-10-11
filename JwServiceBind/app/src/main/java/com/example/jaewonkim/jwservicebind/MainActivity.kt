package com.example.jaewonkim.jwservicebind

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ServiceConnection {
    var isService = false

    override fun onServiceDisconnected(name: ComponentName?) {
        isService = false
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        var myBinder = service as MyService.MyBinder
        var service = myBinder.getService()

        println(service.message)
        isService = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var service = Intent(this, MyService::class.java)
        bindService(service, this, Context.BIND_AUTO_CREATE)

        button_service_state.setOnClickListener {
            if(isService) {
                Toast.makeText(this, "서비스 연결이 완료 되었습니다.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "서비스 연결이 해제 되었습니다.", Toast.LENGTH_LONG).show()
            }
        }

        button_service_detach.setOnClickListener {
            if(isService) {
                unbindService(this)
                //unbind한다고 바로 onServiceDisconnected가 호출되는게 아니어서 여기서 직접 적어줘야함.
                isService = false
            }
        }
    }
}
