package com.product.hawker.qiitaappkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.product.hawker.qiitaappkotlin.client.ArticleClient
import com.product.hawker.qiitaappkotlin.model.User
import com.product.hawker.qiitaappkotlin.model.Article
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // GSON生成
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        // Retrofit生成
        val retrofit = Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        // 記事クライアント生成
        val articleClient = retrofit.create(ArticleClient::class.java)


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
