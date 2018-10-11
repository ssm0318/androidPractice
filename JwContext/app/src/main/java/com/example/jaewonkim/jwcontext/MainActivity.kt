package com.example.jaewonkim.jwcontext

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

//AppCompatActivity가 context를 가지고 있고, Toast에서의 this는 이 AppcompatActivity를 가리킴.
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //MyFragment가 frame layout에 장착되는 것.
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_main, MyFragment()).commit()

        Toast.makeText(this, "프래그먼트 토스트메시지 입니다.", Toast.LENGTH_LONG).show()
    }
}
