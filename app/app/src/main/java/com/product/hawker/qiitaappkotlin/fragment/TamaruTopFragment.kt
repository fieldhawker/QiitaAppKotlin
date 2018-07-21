package com.product.hawker.qiitaappkotlin.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import com.product.hawker.qiitaappkotlin.R
import com.product.hawker.qiitaappkotlin.database.TamaruDatabaseOpenHelper
import android.widget.ArrayAdapter




class TamaruTopFragment : Fragment() {

    // DB接続
//    var database = TamaruDatabaseOpenHelper(this.context).writableDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Fragmentで表示するViewを作成する
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_tamaru_top, container, false)
    }

    // Viewが生成し終わった時に呼ばれる
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TextViewをひも付けます
        val mTextView = view.findViewById<View>(R.id.textTitleView) as TextView

        // Spinnerをひも付けます
        val spinner = view.findViewById<View>(R.id.spinner) as Spinner
        var list = ArrayList<String>()

        // DB接続
        val database = TamaruDatabaseOpenHelper(this.context).readableDatabase
        val cursor = database.query("QUESTION",
                arrayOf("title"),
                null,
                null,
                null,
                null,
                null,
                null
                )
        cursor.use { c ->
            while (c.moveToNext()) {
                val title = c.getString(c.getColumnIndex("title"))

                list.add(title)

            }

        }
        database.close()

        val adapter = ArrayAdapter<String>(
                this.context, android.R.layout.simple_spinner_dropdown_item, list
        )
        spinner.setAdapter(adapter);

        // 入力画面へ遷移
        view.findViewById<View>(R.id.imageViewStart).setOnClickListener {

//            // Fragmentを作成します
//            val fragment = SensorGameFragment()
//            // Fragmentの追加や削除といった変更を行う際は、Transactionを利用します
//            val fragmentManager = fragmentManager
//            val transaction = fragmentManager?.beginTransaction()
//            // 新しく追加を行うのでaddを使用します
//            // 他にも、よく使う操作で、replace removeといったメソッドがあります
//            // メソッドの1つ目の引数は対象のViewGroupのID、2つ目の引数は追加するfragment
//            transaction?.replace(R.id.constraintLayout, fragment)
//            // 最後にcommitを使用することで変更を反映します
//            transaction?.commit()
        }

        // 過去の履歴一覧へ遷移
        view.findViewById<View>(R.id.list_botton).setOnClickListener {

            //            // Fragmentを作成します
//            val fragment = SensorGameFragment()
//            // Fragmentの追加や削除といった変更を行う際は、Transactionを利用します
//            val fragmentManager = fragmentManager
//            val transaction = fragmentManager?.beginTransaction()
//            // 新しく追加を行うのでaddを使用します
//            // 他にも、よく使う操作で、replace removeといったメソッドがあります
//            // メソッドの1つ目の引数は対象のViewGroupのID、2つ目の引数は追加するfragment
//            transaction?.replace(R.id.constraintLayout, fragment)
//            // 最後にcommitを使用することで変更を反映します
//            transaction?.commit()
        }


    }
}