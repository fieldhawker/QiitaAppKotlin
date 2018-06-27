package com.product.hawker.qiitaappkotlin

import android.os.Bundle
import android.widget.Button
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import android.content.Intent

const val MY_REQUEST_CODE = 0

class MainActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 移動ボタン
        val moveButton = findViewById(R.id.move_button) as Button
        // ストップウォッチボタン
        val watchButton = findViewById(R.id.watch_button) as Button

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
    }
}
