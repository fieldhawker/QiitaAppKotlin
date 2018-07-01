package com.product.hawker.qiitaappkotlin.client

import android.content.Context
import android.content.AsyncTaskLoader
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class ClarifaiAsyncClient(context: Context, private val filePath: String) :
        AsyncTaskLoader<List<String>>(context) {

    private var cache: List<String>? = null

    override fun loadInBackground(): List<String> {

        val file = File(filePath)

        val reader = BufferedReader(FileReader(file))
        return reader.readLines()
    }

    override fun deliverResult(data: List<String>?) {
        if (isReset) {
            return
        }

        cache = data
        super.deliverResult(data)
    }

    override fun onStartLoading() {
        if (cache != null) {

            deliverResult(cache)
        }

        if (takeContentChanged() || cache == null) {
            forceLoad()
        }
    }

    override fun onStopLoading() {
        cancelLoad()
    }

    override fun onReset() {
        super.onReset()
        onStopLoading()
        cache = null
    }
}