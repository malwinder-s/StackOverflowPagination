package com.malwinder.example.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    /** Duration of wait  */
    private val SPLASH_DISPLAY_LENGTH = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.malwinder.example.R.layout.activity_splash)
        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/

        GlobalScope.launch {
            delay(SPLASH_DISPLAY_LENGTH)
            val mainIntent = Intent(this@SplashActivity, SearchActivity::class.java)
            this@SplashActivity.startActivity(mainIntent)
            this@SplashActivity.finish()        }
    }
}