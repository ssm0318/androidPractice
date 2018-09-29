package com.example.jaewonkim.jwsharedpreference

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //SharedPreference 객체
        var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        //SharedPreference 수정 객체
        var edit = sharedPreferences.edit()

        var save_string = sharedPreferences.getString("userId", "아이디를 입력해주세요")
        editText_email.setText(save_string)

        button_save.setOnClickListener {
            //sharedPreference의 string을 보여줌.
            //String 이외에도 boolean, int, float 등 putBoolean, getFloat 등의 메소드로 사용 가능.
            edit.putString("userId", editText_email.text.toString()).commit()
        }
    }
}
