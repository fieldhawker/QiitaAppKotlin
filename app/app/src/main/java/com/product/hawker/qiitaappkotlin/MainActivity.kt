package com.product.hawker.qiitaappkotlin

import android.os.Bundle
import android.widget.Button
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import android.content.Intent
import com.product.hawker.qiitaappkotlin.activity.*

const val MY_REQUEST_CODE = 0

class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 移動ボタン
        val moveButton = findViewById(R.id.move_button) as Button
        // ストップウォッチボタン
        val watchButton = findViewById(R.id.watch_button) as Button
        // センサーボタン
        val sensorButton = findViewById(R.id.sensor_button) as Button
        // Viewの移動ボタン
        val moveViewButton = findViewById(R.id.move_view_button) as Button
        // カメラボタン
        val cameraButton = findViewById(R.id.camera_button) as Button
        // 地図ボタン
        val mapsButton = findViewById(R.id.maps_button) as Button
        // センサーゲームボタン
        val sensorGameButton = findViewById(R.id.sensor_game_button) as Button
        // たまる君ボタン
        val tamaruButton = findViewById(R.id.tamaru_button) as Button

        // 移動ボタン
        moveButton.setOnClickListener{
            val intent: Intent = Intent(this, ArticleListActivity::class.java)
            intent.putExtra("number", 120)
            intent.putExtra("string", "The message from MainActivity")

            // 新しくアクティビティを開く
            startActivityForResult(intent, MY_REQUEST_CODE)
        }
        // ストップウォッチボタン
        watchButton.setOnClickListener{
            val intent: Intent = Intent(this, WatchActivity::class.java)
            intent.putExtra("number", 120)
            intent.putExtra("string", "The message from MainActivity")

            // 新しくアクティビティを開く
            startActivityForResult(intent, MY_REQUEST_CODE)
        }
        // センサーボタン
        sensorButton.setOnClickListener{
            val intent: Intent = Intent(this, SensorActivity::class.java)
            intent.putExtra("number", 120)
            intent.putExtra("string", "The message from MainActivity")

            // 新しくアクティビティを開く
            startActivityForResult(intent, MY_REQUEST_CODE)
        }
        // Viewの移動ボタン
        moveViewButton.setOnClickListener{
            val intent: Intent = Intent(this, MoveViewActivity::class.java)
            intent.putExtra("number", 120)
            intent.putExtra("string", "The message from MainActivity")

            // 新しくアクティビティを開く
            startActivityForResult(intent, MY_REQUEST_CODE)
        }
        // カメラボタン
        cameraButton.setOnClickListener{
            val intent: Intent = Intent(this, CameraActivity::class.java)
            intent.putExtra("number", 120)
            intent.putExtra("string", "The message from MainActivity")

            // 新しくアクティビティを開く
            startActivityForResult(intent, MY_REQUEST_CODE)
        }
        // 地図ボタン
        mapsButton.setOnClickListener{
            val intent: Intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("number", 120)
            intent.putExtra("string", "The message from MainActivity")

            // 新しくアクティビティを開く
            startActivityForResult(intent, MY_REQUEST_CODE)
        }
        // センサーゲームボタン
        sensorGameButton.setOnClickListener{
            val intent: Intent = Intent(this, SensorGameActivity::class.java)
            intent.putExtra("number", 120)
            intent.putExtra("string", "The message from MainActivity")

            // 新しくアクティビティを開く
            startActivityForResult(intent, MY_REQUEST_CODE)
        }
        // たまる君ボタン
        tamaruButton.setOnClickListener{
            val intent: Intent = Intent(this, TamaruActivity::class.java)
            intent.putExtra("number", 120)
            intent.putExtra("string", "The message from MainActivity")

            // 新しくアクティビティを開く
            startActivityForResult(intent, MY_REQUEST_CODE)
        }




    }
}
