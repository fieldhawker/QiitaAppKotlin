package com.product.hawker.qiitaappkotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.product.hawker.qiitaappkotlin.R


class SensorGameStartFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Fragmentで表示するViewを作成する
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_sensor_game_start, container, false)
    }

    // Viewが生成し終わった時に呼ばれる
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TextViewをひも付けます
        val mTextView = view.findViewById<View>(R.id.textTitleView) as TextView
        // Buttonのクリックした時の処理を書きます
        view.findViewById<View>(R.id.start_botton).setOnClickListener {

            // Fragmentを作成します
            val fragment = SensorGameFragment()
            // Fragmentの追加や削除といった変更を行う際は、Transactionを利用します
            val fragmentManager = fragmentManager
            val transaction = fragmentManager?.beginTransaction()
            // 新しく追加を行うのでaddを使用します
            // 他にも、よく使う操作で、replace removeといったメソッドがあります
            // メソッドの1つ目の引数は対象のViewGroupのID、2つ目の引数は追加するfragment
            transaction?.replace(R.id.constraintLayout, fragment)
            // 最後にcommitを使用することで変更を反映します
            transaction?.commit()
        }


    }
}