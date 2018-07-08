package com.product.hawker.qiitaappkotlin.fragment

//import com.product.hawker.qiitaappkotlin.R.id.constraintLayout
import android.content.Context
import android.graphics.BitmapRegionDecoder
import android.graphics.Point
import android.graphics.Rect
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.product.hawker.qiitaappkotlin.R
import kotlinx.android.synthetic.main.fragment_sensor_game.*
import java.io.IOException
import java.lang.Math.abs
import java.util.*


class SensorGameFragment : Fragment(), SensorEventListener {
//class SensorGameFragment : Fragment() {

    private var mTextView: TextView? = null

    // 傾き検知
    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null

    private var soundPool: SoundPool? = null
    private var soundId = 0

    var size = Point()          // ディスプレイサイズ
    val pictureSize = 32        // ボールのサイズ
    val goalSize = 64 //1000    // ゴールのサイズ
    var x_goal = 0              // ゴールの開始位置X
    var y_goal = 0              // ゴールの開始位置Y

    var margin_left = 0
    var margin_right = 0
    var margin_top = 0
    var margin_bottom = 0

    var isGoal = false

    val handler = Handler()
    var timeValue = 0
    var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        // Bundleの値を受け取る際はonCreateメソッド内で行う
//        val args = arguments
//        // Bundleがセットされていなかった時はNullなのでNullチェックをする
//        if (args != null) {
//            // String型でNameの値を受け取る
//            mName = args.getString(KEY_NAME)
//            // int型で背景色を受け取る
//            mBackgroundColor = args.getInt(KEY_BACKGROUND, Color.TRANSPARENT)
//        }

        val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        soundPool = SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .build()
        soundId = soundPool!!.load(this.context, R.raw.fall02, 1)


    }


    // Fragmentで表示するViewを作成するメソッド
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)


        // 先ほどのレイアウトをここでViewとして作成します
        return inflater.inflate(R.layout.fragment_sensor_game, container, false)
    }

    // Viewが生成し終わった時に呼ばれるメソッド
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        // TextViewをひも付けます
        mTextView = view.findViewById<View>(R.id.labelText) as TextView
        // Buttonのクリックした時の処理を書きます
        view.findViewById<View>(R.id.button5).setOnClickListener {

            // Fragmentを作成します
            val fragment = SensorGameFragment()
            // Fragmentの追加や削除といった変更を行う際は、Transactionを利用します
            val fragmentManager = fragmentManager

            val transaction = fragmentManager?.beginTransaction()
            // 新しく追加を行うのでaddを使用します
            // 他にも、よく使う操作で、replace removeといったメソッドがあります
            // メソッドの1つ目の引数は対象のViewGroupのID、2つ目の引数は追加するfragment
            transaction?.replace(R.id.constraintLayout, fragment)
            // 最後にcommitを使用することで変更を反映します
            transaction?.commit()


//            mTextView?.setText("ボタンを押したよ")
        }

        val timeText = view.findViewById(R.id.timeText) as TextView
        // 計測
        runnable = object : Runnable {
            override fun run() {
                timeValue++

                timeToText(timeValue)?.let {
                    timeText.text = it
                }
                handler.postDelayed(this, 1000)
            }
        }

        handler.post(runnable)


        // 画面の中央を求める？
        val wm = getActivity()?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val disp = wm.defaultDisplay


        disp.getSize(size)

//        Log.d("display_size_x", size.x.toString())
//        Log.d("display_size_y", size.y.toString())

        margin_left = pictureSize
        margin_right = (size.x - pictureSize)
        margin_top = (pictureSize * 3)
        margin_bottom = (size.y - pictureSize)

        Log.d("margin_left", margin_left.toString())
        Log.d("margin_right", margin_right.toString())
        Log.d("margin_top", margin_top.toString())
        Log.d("margin_bottom", margin_bottom.toString())

        val centerX = size.x / 2
        val centerY = size.y / 2

        x_goal = centerX
        y_goal = centerY

        val rand = Random()
//        var tmp_right = (pictureSize + margin_left)
//        var tmp_left = (size.x - margin_right - goalSize - (pictureSize * 3)) + tmp_right
//        var tmp_top = (pictureSize + margin_top)
//        var tmp_bottom = (size.y - margin_bottom - goalSize - (pictureSize * 3)) + tmp_top
        while (abs(x_goal - centerX) < pictureSize * 2) {
            x_goal = rand.nextInt(margin_right - margin_left) + margin_left
        }
        while (abs(y_goal - centerY) < pictureSize * 2) {
            y_goal = rand.nextInt(margin_bottom - margin_top) + margin_top
        }
        Log.d("x_goal", x_goal.toString())
        Log.d("y_goal", y_goal.toString())


//        val constraintLayout = findViewById(R.id.constraintLayout) as ConstraintLayout

        // imageViewを作成
        val imageView = ImageView(getActivity())
        imageView.setTag("imageView")
        imageView.setImageResource(R.drawable.android_logo)

        var imageWidth = pictureSize
        var imageHeight = pictureSize
        imageView.layoutParams = LinearLayout.LayoutParams(imageWidth, imageHeight)
        imageView.x = centerX.toFloat()
        imageView.y = centerY.toFloat()

        constraintLayout.addView(imageView)

        // layout上のimageViewを使用
//        imageView2.setTag("imageView2")
//
//        imageWidth = pictureSize * goalSize
//        imageHeight = pictureSize * goalSize
//        imageView2.layoutParams = LinearLayout.LayoutParams(imageWidth, imageHeight)
//        imageView2.x = 300.toFloat()
//        imageView2.y = 450.toFloat()

        val file = Environment.getExternalStorageDirectory()
        val file_inputstream = getResources().openRawResource(+R.drawable.worldmap_c)

        val imageView3 = ImageView(getActivity())
        imageView3.setTag("imageView3")
        imageView3.setScaleType(ImageView.ScaleType.FIT_CENTER)

        try {
            val regionDecoder: BitmapRegionDecoder
            regionDecoder = BitmapRegionDecoder.newInstance(file_inputstream,
                    false)
            val rect = Rect(32, 0, 64, 32)
            val bitmap = regionDecoder.decodeRegion(rect, null)
            imageView3.setImageBitmap(bitmap)
        } catch (e: IOException) {
            e.printStackTrace()
        }



        imageWidth = goalSize
        imageHeight = goalSize
        imageView3.layoutParams = LinearLayout.LayoutParams(imageWidth, imageHeight)
        imageView3.x = x_goal.toFloat()
        imageView3.y = y_goal.toFloat()

        constraintLayout.addView(imageView3)

    }


    override fun onResume() {
        super.onResume()


//        constraintLayout.addView(imageView2)


        // get reference of the service
        mSensorManager = getActivity()?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        // focus in accelerometer
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (mAccelerometer != null) {
            mSensorManager?.registerListener(this, mAccelerometer,
                    SensorManager.SENSOR_DELAY_GAME) // SENSOR_DELAY_NORMAL
        }


    }

    public override fun onStop() {
        super.onStop()
        mSensorManager?.unregisterListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        soundPool!!.unload(soundId)
        soundPool!!.release()

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {

        val coe = 2 // 移動係数
//        Log.d("sensor_change", "changed!")

        // ビューオブジェクトを取得
//        val constraintLayout = findViewById<View>(R.id.constraintLayout) as ConstraintLayout
        val imageView = constraintLayout.findViewWithTag("imageView") as ImageView

//        val xText = constraintLayout.findViewById<View>(R.id.xText) as TextView
//        val yText = view?.findViewById<View>(R.id.yText) as TextView
//        val zText = view?.findViewById<View>(R.id.zText) as TextView

        if (event.sensor == mAccelerometer) {

            Log.d("display_size_x", size.x.toString())
            Log.d("display_size_y", size.y.toString())

//            Log.d("0", event.values[0].toString())
//            Log.d("1", event.values[1].toString())
//            Log.d("2", event.values[2].toString())

            val x = coe * event.values[0]
            val y = coe * event.values[1]
            val z = coe * event.values[2]

            val x_over = ((size.x - imageView.x) < margin_left)
            val x_under = ((size.x - imageView.x) > margin_right)
            val y_over = ((size.y - imageView.y) < margin_top)
            val y_under = ((size.y - imageView.y) > margin_bottom)
//            val x_over = ((size.x - imageView.x) < pictureSize)
//            val x_under = ((size.x - imageView.x) > (size.x - pictureSize))
//            val y_over = ((size.y - imageView.y) < pictureSize * 3)
//            val y_under = ((size.y - imageView.y) > (size.y - pictureSize))

            if (!x_over && !x_under)
                imageView.x -= x
            else if (x_over && 0 < x)
                imageView.x -= x
            else if (x_under && x < 0)
                imageView.x -= x

            if (!y_over && !y_under)
                imageView.y += y
            else if (y_over && y < 0)
                imageView.y += y
            else if (y_under && 0 < y)
                imageView.y += y


            // 移動量
            azimuthText.text = x.toString()
            pitchText.text = y.toString()
            rollText.text = z.toString()
//            Log.d("move_x", x.toString())
//            Log.d("move_y", y.toString())


            // 画像の座標
            xText.text = imageView.x.toString()
            yText.text = imageView.y.toString()
//            Log.d("position_x", imageView.x.toString())
//            Log.d("position_y", imageView.y.toString())

            Log.d("goal_x", (x_goal + goalSize).toString())
            Log.d("goal_y", (y_goal + goalSize).toString())

            if ((x_goal <= imageView.x && imageView.x <= (x_goal + goalSize)) && (y_goal <= imageView.y && imageView.y <= (y_goal + goalSize))) {

                if (isGoal) {
                    // 何もしない
                } else {

                    handler.removeCallbacks(runnable)

                    soundPool!!.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)

//                    Toast.makeText(getActivity(), "ゴールに入りました。", Toast.LENGTH_LONG).show()
                    Log.d("position_x", imageView.x.toString())
                    Log.d("position_y", imageView.y.toString())
                    Log.d("goal", "ゴールに入りました。")


                    val bundle = Bundle()
                    bundle.putInt("timeValue", timeValue)

                    // Fragmentを作成します
                    val fragment = SensorGameGoalFragment()
                    // Fragmentの追加や削除といった変更を行う際は、Transactionを利用します
                    val fragmentManager = fragmentManager
                    fragment.setArguments(bundle)
                    val transaction = fragmentManager?.beginTransaction()
                    // 新しく追加を行うのでaddを使用します
                    // 他にも、よく使う操作で、replace removeといったメソッドがあります
                    // メソッドの1つ目の引数は対象のViewGroupのID、2つ目の引数は追加するfragment
                    transaction?.replace(R.id.constraintLayout, fragment)
                    // 最後にcommitを使用することで変更を反映します
                    transaction?.commit()

                    isGoal = true
                }
            } else {
                isGoal = false
            }

        }
    }

    private fun timeToText(time: Int = 0): String? {

        return if (time < 0) {
            null
        } else if (time == 0) {
            "00:00:00"
        } else {
            val h = time / 3600
            val m = time % 3600 / 60
            val s = time % 60
            "%1$02d:%2$02d:%3$02d".format(h, m, s)
        }
    }
//    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
//
//        when (motionEvent.action) {
//            MotionEvent.ACTION_DOWN -> {
//                // ビューオブジェクトを取得
////                val constraintLayout = view?.findViewById<View>(R.id.constraintLayout) as ConstraintLayout
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