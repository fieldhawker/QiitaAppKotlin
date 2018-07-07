package com.product.hawker.qiitaappkotlin.activity


import android.content.Context
import com.product.hawker.qiitaappkotlin.R
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
//import android.support.v4.app.LoaderManager
//import android.app.LoaderManager.LoaderCallbacks
import android.support.v4.app.LoaderManager.LoaderCallbacks
import android.support.v4.content.Loader

import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
//import com.bumptech.glide.load.model.FileLoader
import com.product.hawker.qiitaappkotlin.service.FileLoader

//import com.product.hawker.qiitaappkotlin.client.ClarifaiAsyncClient
//import android.content.Context
//import android.support.v4.app.LoaderManager
//import android.support.v4.content.Loader
//import android.widget.TextView

class CameraActivity : AppCompatActivity() {
//class CameraActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<List<String>> {

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
//
//        getSharedPreferences("sample", Context.MODE_PRIVATE).edit()
//                .putInt("age", 30)
//                .putString("name", "Tarou")
//                .commit()

//        val arguments = Bundle()
//        arguments.putString("file_path",
//                applicationInfo.dataDir + "/shared_prefs/sample.xml")
//
//        getLoaderManager.initLoader(1, arguments, this)
//
//        getLoaderManager().initLoader(LOADER_ID, bundle, this);

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

//    override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<String>> {
//        val filePath = args?.getString("file_path")
//        ?: throw IllegalArgumentException("file path not specified")
//
//        return  FileLoader(this, filePath)
//
//    }
//
//    override fun onLoadFinished(loader: Loader<List<String>>, data: List<String>?) {
//
//        if (data != null) {
//
//            val fileContent = data.joinToString("\n")
//
//            val content = findViewById<TextView>(R.id.fileContent)
//            content.text = fileContent
//        }
//    }
//
//    override fun onLoaderReset(p0: Loader<List<String>>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
}