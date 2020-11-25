package com.e.mysensordata2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity(), SensorEventListener {

    private var mSensorManager: SensorManager? = null

    private var mSensorLight: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mSensorManager =
                getSystemService(Context.SENSOR_SERVICE) as SensorManager?

        mSensorLight = mSensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onStart() {
        super.onStart()

        if (mSensorLight != null) {
            mSensorManager!!.registerListener(
                    this, mSensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onStop() {
        super.onStop()

        mSensorManager!!.unregisterListener(this);
    }

    override fun onSensorChanged(event: SensorEvent?) {
        var sensorType = event!!.sensor.type
        var currentValue = event.values[0]

        when(sensorType){
            Sensor.TYPE_LIGHT -> {
                lightView.text = currentValue.toString()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }


}