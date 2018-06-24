package com.product.hawker.qiitaappkotlin

import com.product.hawker.qiitaappkotlin.model.*
import com.product.hawker.qiitaappkotlin.view.*

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ArticleViewオブジェクトを生成
        val articleView = ArticleView(applicationContext)

        // Articleオブジェクトを生成してArticleViewオブジェクトに設定
        articleView.setArticle(Article(id = "124",
                title = "Kotlin入門",
                url = "http://www.example.com/articles/124",
                user = User(id = "456", name = "たろう", profileImageUrl = "")))

        setContentView(articleView)
//        setContentView(R.layout.activity_main)
    }
}
