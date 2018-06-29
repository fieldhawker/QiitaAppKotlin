package com.product.hawker.qiitaappkotlin

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.math.PI
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth




// 向き情報を格納するクラス
data class Orientation(
        val azimuth: Float,
        val pitch: Float,
        val roll: Float
)

class OrientationLiveData(
        context: Context,
        private val sensorDelay: Int = SensorManager.SENSOR_DELAY_UI)
    : LiveData<Orientation>(), SensorEventListener {

    private val mSensorManager: SensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accelerometer: Sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val magneticField: Sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    private val mAccelerometerReading = FloatArray(3)
    private val mMagnetometerReading = FloatArray(3)

    private val mRotationMatrix = FloatArray(9)
    private val mOrientationAngles = FloatArray(3)

    override fun onActive() {
        super.onActive()

        mSensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL, sensorDelay)
        mSensorManager.registerListener(this, magneticField, SensorManager.SENSOR_DELAY_NORMAL, sensorDelay)
    }

    override fun onInactive() {
        super.onInactive()
        mSensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    override fun onSensorChanged(event: SensorEvent) {

        if (event.sensor == accelerometer) {
            System.arraycopy(event.values, 0, mAccelerometerReading, 0, mAccelerometerReading.size)
        } else if (event.sensor == magneticField) {
            System.arraycopy(event.values, 0, mMagnetometerReading, 0, mMagnetometerReading.size)
        }

        updateOrientationAngles()

        value = Orientation(mOrientationAngles[0], mOrientationAngles[1], mOrientationAngles[2])
    }

    private fun updateOrientationAngles() {
        SensorManager.getRotationMatrix(mRotationMatrix, null, mAccelerometerReading, mMagnetometerReading)
        SensorManager.getOrientation(mRotationMatrix, mOrientationAngles)
    }
}

class SensorActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)


        // ビューオブジェクトを取得
        val azimuthText = findViewById(R.id.azimuthText) as TextView
        val pitchText = findViewById(R.id.pitchText) as TextView
        val rollText = findViewById(R.id.rollText) as TextView

        val widnwoWidth = getWindowManager()?.getDefaultDisplay()
        getWindowManager()?.getDefaultDisplay()?.getWidth()!!


        val constraintLayout = findViewById(R.id.constraintLayout) as ConstraintLayout
        val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.android_logo)

        val imageWidth = 50
        val imageHeight = 50
        imageView.layoutParams = LinearLayout.LayoutParams(imageWidth, imageHeight)

        constraintLayout.addView(imageView)


        OrientationLiveData(this).observe(this, Observer { orientation ->

            if (orientation == null) return@Observer

            // orientation.azimuth Z軸の回転
            // orientation.pitch X軸の回転
            // orientation.roll  Y軸の回転
            // で処理を行う
//            azimuthText.text = orientation.azimuth.toString()
//            pitchText.text = orientation.pitch.toString()
//            rollText.text = orientation.roll.toString()
            azimuthText.text = Math.round(orientation.azimuth * 180 / PI).toString()
            pitchText.text = Math.round(orientation.pitch * 180 / PI).toString()
            rollText.text = Math.round(orientation.roll * 180 / PI).toString()

            val x = 1 * Math.cos(orientation.pitch.toDouble())
            val y = 1 * Math.cos(orientation.roll.toDouble())
            pitchText.text = x.toString()
            rollText.text = y.toString()
            val left = x
            val top = y
            val right = x / 2 + imageView.width
            val bottom = y + imageView.height
            imageView.layout(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())
//            imageView.setTranslationX(x.toFloat())
//            imageView.setTranslationY(y.toFloat())

        })
    }

}