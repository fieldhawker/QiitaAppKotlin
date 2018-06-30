package com.product.hawker.qiitaappkotlin.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import com.product.hawker.qiitaappkotlin.R

class CameraActivity : AppCompatActivity() {

    private val RESULT_CAMERA = 1001

    override fun onCreate(savedInstantState: Bundle?) {
        super.onCreate(savedInstantState)
        setContentView(R.layout.activity_camera)

        // ボタンの取得
        val button = findViewById(R.id.button) as Button

        // 以下、ボタンの操作

        button.setOnClickListener {
            // カメラで写真撮影
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, RESULT_CAMERA)
        }

    }

    // 結果を受け取る
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val imageView = findViewById(R.id.imageView) as ImageView

        if (requestCode == RESULT_CAMERA) {
            val bitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(bitmap) // ImageView に画像をセット
        }

    }
}