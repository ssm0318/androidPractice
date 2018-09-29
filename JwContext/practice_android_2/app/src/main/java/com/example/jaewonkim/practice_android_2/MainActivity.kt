package com.example.jaewonkim.practice_android_2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jw_button.setOnClickListener {
            var str = jw_editText.text
            println(str)
            jw_textView.text = str
            jw_imageView.setImageResource(R.drawable.`image2`)
        }
    }
}
