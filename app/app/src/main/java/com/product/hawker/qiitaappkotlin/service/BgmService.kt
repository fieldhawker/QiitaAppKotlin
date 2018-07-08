package com.product.hawker.qiitaappkotlin.service


import android.content.Context
import android.media.MediaPlayer
import com.product.hawker.qiitaappkotlin.R

class BgmService(context: Context) {

    private val mediaPlayer: MediaPlayer

    init {
        // BGMファイルを読み込む
        this.mediaPlayer = MediaPlayer.create(context, R.raw.spadenoheitai)
        // ループ再生
        this.mediaPlayer.isLooping = true
        // 音量設定
        this.mediaPlayer.setVolume(1.0f, 1.0f)
    }

    /**
     * BGMを再生する
     */
    fun start() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.seekTo(0)
            mediaPlayer.start()
        }
    }

    /**
     * BGMを停止する
     */
    fun stop() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.prepareAsync()
        }
    }
}