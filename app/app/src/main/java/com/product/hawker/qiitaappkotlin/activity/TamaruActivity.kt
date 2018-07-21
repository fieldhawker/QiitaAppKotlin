package com.product.hawker.qiitaappkotlin.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.product.hawker.qiitaappkotlin.R
import com.product.hawker.qiitaappkotlin.fragment.TamaruTopFragment
import com.product.hawker.qiitaappkotlin.service.BgmService

class TamaruActivity : AppCompatActivity() {

    private var bgm: BgmService? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tamaru)

        bgm = BgmService(this)

        // Fragmentを作成します
        val fragment = TamaruTopFragment()
        // Fragmentの追加や削除といった変更を行う際は、Transactionを利用します
        val transaction = supportFragmentManager.beginTransaction()
        // 新しく追加を行うのでaddを使用します
        // 他にも、よく使う操作で、replace removeといったメソッドがあります
        // メソッドの1つ目の引数は対象のViewGroupのID、2つ目の引数は追加するfragment
        transaction.add(R.id.constraintLayout, fragment)
        // 最後にcommitを使用することで変更を反映します
        transaction.commit()

    }

    override fun onResume() {
        super.onResume()
        //BGMの再生
        bgm?.start()
    }

    override fun onPause() {
        super.onPause()
        //BGMの再生
        bgm?.stop()
    }

}