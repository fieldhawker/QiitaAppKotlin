package com.product.hawker.qiitaappkotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.product.hawker.qiitaappkotlin.R
import java.util.*

//import android.provider.ContactsContract.Directory.PACKAGE_NAME







class SensorGameGoalFragment : Fragment() {

    val packageName = "com.product.hawker.qiitaappkotlin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // Fragmentで表示するViewを作成する
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_sensor_game_goal, container, false)
    }

    // Viewが生成し終わった時に呼ばれる
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TextViewをひも付けます
        val textTitleView = view.findViewById<View>(R.id.textTitleView) as TextView
        // imageViewをひも付けます
        val imageView = view.findViewById<View>(R.id.imageView3) as ImageView

        val rand = Random()
        val img_no = "img%03d".format(rand.nextInt(14))

        val rid = resources.getIdentifier(img_no, "drawable", packageName)
        imageView.setImageResource(rid)

        val bundle = arguments
        val timeValue = bundle!!.getInt("timeValue").toString()
        val buybooks = getString(R.string.sensor_game_goal_text, timeValue)
        textTitleView.text = buybooks

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