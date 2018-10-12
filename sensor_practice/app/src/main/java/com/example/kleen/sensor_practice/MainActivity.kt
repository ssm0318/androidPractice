package com.example.kleen.sensor_practice

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // 2.3부터 다시해보기

    override fun onSensorChanged(event: SensorEvent?) {
        val sensorType = event!!.sensor.type
        val currentValue = event.values[0]

        when (sensorType) {
            // Event came from the light sensor.
            Sensor.TYPE_LIGHT -> {
                mTextSensorLight.setText(resources.getString(
                        R.string.label_light, currentValue))
            }
        }// Handle light sensor
        // do nothing
    }

    private var mSensorManager: SensorManager? = null
    // Individual light and proximity sensors.
    private val mSensorProximity: Sensor? = null
    private val mSensorLight: Sensor? = null

    // TextViews to display current sensor values
    private var mTextSensorLight: TextView? = null
    private var mTextSensorProximity: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager?

//        val sensorList = mSensorManager!!.getSensorList(Sensor.TYPE_ALL)
//
//        val sensorText = StringBuilder()
//
//        for (currentSensor in sensorList) {
//            sensorText.append(currentSensor.name).append(
//                    System.getProperty("line.separator"))
//        }
//
//        val sensorTextView = findViewById<View>(R.id.sensor_list) as TextView
//        sensorTextView.text = sensorText

        val mTextSensorLight = findViewById<TextView>(R.id.label_light)
        val mTextSensorProximity = findViewById<TextView>(R.id.label_proximity)

        val mSensorProximity = mSensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        val mSensorLight = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)

        val sensor_error = resources.getString(R.string.error_no_sensor)

        if (mSensorLight == null) {
            mTextSensorLight.text = sensor_error
        }

        if (mSensorProximity == null) {
            mTextSensorProximity.text = sensor_error
        }
    }

    override fun onStart() {
        super.onStart()

        if (mSensorProximity != null) {
            mSensorManager.registerListener(this, mSensorProximity,
                    SensorManager.SENSOR_DELAY_NORMAL)
        }
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onStop() {
        super.onStop()
        mSensorManager.unregisterListener(this)
    }
}
