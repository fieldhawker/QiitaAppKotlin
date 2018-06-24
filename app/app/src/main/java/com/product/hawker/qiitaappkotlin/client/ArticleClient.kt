package com.product.hawker.qiitaappkotlin.client

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import com.product.hawker.qiitaappkotlin.model.Article

interface ArticleClient {

    @GET("/api/v2/items")
    fun search(@Query("query") query: String): Observable<List<Article>>

}