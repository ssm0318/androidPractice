package com.example.jaewonkim.jwrequestpermission

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var calendar = Manifest.permission.READ_CALENDAR
        var camera = Manifest.permission.CAMERA
        var contact = Manifest.permission.READ_CONTACTS

        var location = Manifest.permission.ACCESS_FINE_LOCATION
        var microphone = Manifest.permission.RECORD_AUDIO
        var phone = Manifest.permission.READ_PHONE_STATE

        var sensor = Manifest.permission.BODY_SENSORS
        var sms = Manifest.permission.SEND_SMS
        var storage = Manifest.permission.READ_EXTERNAL_STORAGE

        // 마시멜로 단계 이상 버전에서만 아래 코드 작동
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(calendar, camera, contact, location, microphone, phone, sensor, sms, storage), 0)
        }
    }

    //permissions는 권한의 이름, grantResults는 권한 받았는지 여부
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0) {
            for(i in permissions.indices) {
                //Run 터미널에서 -1 (허가)인지 0 (허가 X)인지 확인 가능
                println("권한 : " + permissions[i] + "허가 상태 : " + grantResults[i])
            }
        }
    }
}
