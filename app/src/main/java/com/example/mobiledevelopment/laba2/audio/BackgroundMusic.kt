package com.example.mobiledevelopment.laba2.audio

import android.content.Context
import android.media.MediaPlayer
import com.example.mobiledevelopment.R

class BackgroundMusic(context: Context) {
    private val appContext = context.applicationContext
    private var player: MediaPlayer? = null

    fun start() {
        if (player != null) return

        player = MediaPlayer.create(appContext, R.raw.background_music)?.apply {
            isLooping = true
            setVolume(0.35f, 0.35f)
            start()
        }
    }

    fun stop() {
        player?.release()
        player = null
    }
}