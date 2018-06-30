package com.product.hawker.qiitaappkotlin

import android.content.Context
import android.graphics.Point
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class SensorActivity : AppCompatActivity(), SensorEventListener {

    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null

    var size = Point()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        val picture_size = 50


        // 画面の中央を求める？
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val disp = wm.defaultDisplay


        disp.getSize(size)

        Log.d("display_size_x", size.x.toString())
        Log.d("display_size_y", size.y.toString())


        val centerX = size.x / 2
        val centerY = size.y / 2

        Log.d("centerX", centerX.toString())
        Log.d("centerY", centerY.toString())


        val constraintLayout = findViewById(R.id.constraintLayout) as ConstraintLayout


        val imageView = ImageView(this)
        imageView.setTag("imageView")
        imageView.setImageResource(R.drawable.android_logo)

        val imageWidth = picture_size
        val imageHeight = picture_size
        imageView.layoutParams = LinearLayout.LayoutParams(imageWidth, imageHeight)
        imageView.x = centerX.toFloat()
        imageView.y = centerY.toFloat()


        constraintLayout.addView(imageView)


        // get reference of the service
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        // focus in accelerometer
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    }

    override fun onResume() {
        super.onResume()
        if (mAccelerometer != null) {
            mSensorManager?.registerListener(this, mAccelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    public override fun onStop() {
        super.onStop()
        mSensorManager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {

        val margin_x = 100
        val margin_y = 200


        // ビューオブジェクトを取得
        val constraintLayout = findViewById(R.id.constraintLayout) as ConstraintLayout
        val imageView = constraintLayout.findViewWithTag("imageView") as ImageView

        val xText = findViewById(R.id.xText) as TextView
        val yText = findViewById(R.id.yText) as TextView
        val zText = findViewById(R.id.zText) as TextView

        if (event.sensor == mAccelerometer) {

            Log.d("0", event.values[0].toString())
            Log.d("1", event.values[1].toString())
            Log.d("2", event.values[2].toString())

            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            if (size.x - margin_x > imageView.x && imageView.x > margin_x)
                imageView.x -= x
            else if (size.x - margin_x <= imageView.x && 0 > x)
                imageView.x -= x
            else if (imageView.x <= margin_x && x > 0)
                imageView.x -= x

            if (size.y - margin_y > imageView.y && imageView.y > margin_y)
                imageView.y += y
            else if (size.y - margin_y <= imageView.y && 0 > y)
                imageView.y += y
            else if (imageView.y <= margin_y && y > 0)
                imageView.y += y


            zText.text = z.toString()

            // 移動量
            xText.text = x.toString()
            yText.text = y.toString()
            Log.d("move_x", x.toString())
            Log.d("move_y", y.toString())


            // 画像の座標
            xText.text = imageView.x.toString()
            yText.text = imageView.y.toString()
            Log.d("position_x", imageView.x.toString())
            Log.d("position_y", imageView.y.toString())

        }
    }

}