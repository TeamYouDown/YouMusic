package com.zionhuang.music

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {
    private var activityStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        activityStarted = true
        val timerThread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    if (activityStarted && intent != null && intent.flags and Intent.FLAG_ACTIVITY_REORDER_TO_FRONT !== 0) {
                        finish()
                    } else {
                        val intent = Intent(this@SplashActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
        timerThread.start()
    }

     override fun onPause() {
        super.onPause()
        finish()
    }
}