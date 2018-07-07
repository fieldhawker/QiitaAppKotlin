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
import android.view.MotionEvent
import com.product.hawker.qiitaappkotlin.fragment.SensorGameStartFragment




//class SensorGameActivity : AppCompatActivity(), SensorEventListener {
    class SensorGameActivity : AppCompatActivity() {

    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null

    var size = Point()
    val pictureSize = 50

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor_game)

        // Fragmentを作成します
        val fragment = SensorGameStartFragment()
        // Fragmentの追加や削除といった変更を行う際は、Transactionを利用します
        val transaction = supportFragmentManager.beginTransaction()
        // 新しく追加を行うのでaddを使用します
        // 他にも、よく使う操作で、replace removeといったメソッドがあります
        // メソッドの1つ目の引数は対象のViewGroupのID、2つ目の引数は追加するfragment
        transaction.add(R.id.constraintLayout, fragment)
        // 最後にcommitを使用することで変更を反映します
        transaction.commit()


//        // 画面の中央を求める？
//        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
//        val disp = wm.defaultDisplay
//
//
//        disp.getSize(size)
//
//        Log.d("display_size_x", size.x.toString())
//        Log.d("display_size_y", size.y.toString())
//
//
//        val centerX = size.x / 2
//        val centerY = size.y / 2
//
//        Log.d("centerX", centerX.toString())
//        Log.d("centerY", centerY.toString())
//
//
//        val constraintLayout = findViewById(R.id.constraintLayout) as ConstraintLayout
//
//
//        val imageView = ImageView(this)
//        imageView.setTag("imageView")
//        imageView.setImageResource(R.drawable.android_logo)
//
//        val imageWidth = pictureSize
//        val imageHeight = pictureSize
//        imageView.layoutParams = LinearLayout.LayoutParams(imageWidth, imageHeight)
//        imageView.x = centerX.toFloat()
//        imageView.y = centerY.toFloat()
//
//
//        constraintLayout.addView(imageView)
//
//
//        // get reference of the service
//        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
//        // focus in accelerometer
//        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    }

//    override fun onResume() {
//        super.onResume()
//        if (mAccelerometer != null) {
//            mSensorManager?.registerListener(this, mAccelerometer,
//                    SensorManager.SENSOR_DELAY_GAME) // SENSOR_DELAY_NORMAL
//        }
//    }
//
//    public override fun onStop() {
//        super.onStop()
//        mSensorManager?.unregisterListener(this)
//    }
//
//    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//    }
//
//    override fun onSensorChanged(event: SensorEvent) {
//
//        val coe = 2 // 移動係数
//
//        // ビューオブジェクトを取得
//        val constraintLayout = findViewById(R.id.constraintLayout) as ConstraintLayout
//        val imageView = constraintLayout.findViewWithTag("imageView") as ImageView
//
//        val xText = findViewById(R.id.xText) as TextView
//        val yText = findViewById(R.id.yText) as TextView
//        val zText = findViewById(R.id.zText) as TextView
//
//        if (event.sensor == mAccelerometer) {
//
//            Log.d("display_size_x", size.x.toString())
//            Log.d("display_size_y", size.y.toString())
//
//            Log.d("0", event.values[0].toString())
//            Log.d("1", event.values[1].toString())
//            Log.d("2", event.values[2].toString())
//
//            val x = coe * event.values[0]
//            val y = coe * event.values[1]
//            val z = coe * event.values[2]
//
//            val x_over = ((size.x - imageView.x) < pictureSize)
//            val x_under = ((size.x - imageView.x) > (size.x - pictureSize))
//            val y_over = ((size.y - imageView.y) < pictureSize * 3)
//            val y_under = ((size.y - imageView.y) > (size.y - pictureSize))
//
//            if (!x_over && !x_under)
//                imageView.x -= x
//            else if (x_over && 0 < x)
//                imageView.x -= x
//            else if (x_under && x < 0)
//                imageView.x -= x
//
//            if (!y_over && !y_under)
//                imageView.y += y
//            else if (y_over && y < 0)
//                imageView.y += y
//            else if (y_under && 0 < y)
//                imageView.y += y
//
//            zText.text = z.toString()
//
//            // 移動量
//            xText.text = x.toString()
//            yText.text = y.toString()
//            Log.d("move_x", x.toString())
//            Log.d("move_y", y.toString())
//
//
//            // 画像の座標
//            xText.text = imageView.x.toString()
//            yText.text = imageView.y.toString()
//            Log.d("position_x", imageView.x.toString())
//            Log.d("position_y", imageView.y.toString())
//
//        }
//    }
//
//
//    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
//
//        when (motionEvent.action) {
//            MotionEvent.ACTION_DOWN -> {
//                // ビューオブジェクトを取得
//                val constraintLayout = findViewById(R.id.constraintLayout) as ConstraintLayout
//                val imageView = constraintLayout.findViewWithTag("imageView") as ImageView
//
//                // タップした位置に移動
//                imageView.x = motionEvent.x
//                imageView.y = motionEvent.y
//            }
//        }
//        return false
//    }
}