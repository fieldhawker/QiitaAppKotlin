package com.product.hawker.qiitaappkotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.product.hawker.qiitaappkotlin.client.ArticleClient
import com.product.hawker.qiitaappkotlin.model.User
import com.product.hawker.qiitaappkotlin.model.Article
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ArticleListActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)


        // 一覧表示用リスト
        val listView: ListView = findViewById(R.id.list_view) as ListView

        // プログレスバー
        val progressBar = findViewById(R.id.progress_bar) as ProgressBar

        // 検索ボタン
        val queryEditText = findViewById(R.id.query_edit_text) as EditText
        val searchButton = findViewById(R.id.search_button) as Button

        // 記事一覧用アダプタ
        val listAdapter = ArticleListAdapter(applicationContext)

//        // ダミーデータ投入
//        listAdapter.articles = listOf(dummyArticle("Kotlin入門", "たろう"),
//                dummyArticle("Java入門", "じろう"))

        // 一覧の要素をクリックした際の挙動
        listView.adapter = listAdapter
        listView.setOnItemClickListener { adapterView, view, position, id ->
            val intent = ArticleActivity.intent(this, listAdapter.articles[position])
            startActivity(intent)
        }

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

        // 検索ボタン
        searchButton.setOnClickListener{

            // クリック時にまずはプログレスバーを表示
            progressBar.visibility = View.VISIBLE

            articleClient.search(queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate{

                        // 終了時にプログレスバーを非表示に
                        progressBar.visibility = View.GONE

                    }
                    .bindToLifecycle(this)
                    .subscribe({
                        queryEditText.text.clear()
                        listAdapter.articles = it
                        listAdapter.notifyDataSetChanged()
                    }, {
                        toast("エラー：$it")
                    })
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
