package com.product.hawker.qiitaappkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.product.hawker.qiitaappkotlin.model.User
import com.product.hawker.qiitaappkotlin.model.Article


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listAdapter = ArticleListAdapter(applicationContext)
        listAdapter.articles = listOf(dummyArticle("Kotlin入門", "たろう"),
                dummyArticle("Java入門", "じろう"))

        val listView: ListView = findViewById(R.id.list_view) as ListView
        listView.adapter = listAdapter
        listView.setOnItemClickListener { adapterView, view, position, id ->
            val article = listAdapter.articles[position]
            ArticleActivity.intent(this, article).let { startActivity(it)}
        }
    }

    // ダミー記事を生成するメソッド
    private fun dummyArticle(title: String, userName: String): Article =
            Article(id = "",
                    title = title,
                    url = "https://kotlinlang.org/",
                    user = User(id = "", name = userName, profileImageUrl = ""))

// 2
//        // ArticleViewオブジェクトを生成
//        val articleView = ArticleView(applicationContext)
//
//        // Articleオブジェクトを生成してArticleViewオブジェクトに設定
//        articleView.setArticle(Article(id = "124",
//                title = "Kotlin入門",
//                url = "http://www.example.com/articles/124",
//                user = User(id = "456", name = "たろう", profileImageUrl = "")))
//
//        setContentView(articleView)


// 1
//        setContentView(R.layout.activity_main)
}
