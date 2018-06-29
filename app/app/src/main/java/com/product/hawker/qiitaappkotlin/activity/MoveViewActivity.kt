package com.product.hawker.qiitaappkotlin.activity

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import com.product.hawker.qiitaappkotlin.R


class MoveViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstantState: Bundle?) {
        super.onCreate(savedInstantState)
        setContentView(R.layout.activity_move_view)

        // 増減
        val point = 50

        // ボタン
        val upButton = findViewById(R.id.button) as Button
        val downButton = findViewById(R.id.button2) as Button
        val rightButton = findViewById(R.id.button3) as Button
        val leftButton = findViewById(R.id.button4) as Button

        // 画面の中央を求める？
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val disp = wm.defaultDisplay
        val size = Point()
        disp.getSize(size)

        val centerX = size.x / 2
        val centerY = size.y / 2

        // フレームレイアウトにイメージビューを配置
        val frameLayout = findViewById(R.id.frameLayout) as FrameLayout

        val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.android_logo)

        val imageWidth = 50
        val imageHeight = 50
        imageView.layoutParams = LinearLayout.LayoutParams(imageWidth, imageHeight)
        imageView.x = centerX.toFloat()
        imageView.y = centerY.toFloat()

        frameLayout.addView(imageView)

        // 以下、ボタンの操作

        upButton.setOnClickListener {

            imageView.x = imageView.x + 0
            imageView.y = imageView.y - point

        }

        downButton.setOnClickListener {

            imageView.x = imageView.x + 0
            imageView.y = imageView.y + point

        }

        rightButton.setOnClickListener {

            imageView.x = imageView.x + point
            imageView.y = imageView.y + 0

        }

        leftButton.setOnClickListener {

            imageView.x = imageView.x - point
            imageView.y = imageView.y + 0

        }
    }
}