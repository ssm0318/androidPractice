package com.example.kleen.sensor_practice

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView



class MainActivity : AppCompatActivity() {
    private var mSensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?

        val sensorList = mSensorManager!!.getSensorList(Sensor.TYPE_ALL)

        val sensorText = StringBuilder()

        for (currentSensor in sensorList) {
            sensorText.append(currentSensor.name).append(
                    System.getProperty("line.separator"))
        }

        val sensorTextView = findViewById<View>(R.id.sensor_list) as TextView
        sensorTextView.text = sensorText
    }
}
