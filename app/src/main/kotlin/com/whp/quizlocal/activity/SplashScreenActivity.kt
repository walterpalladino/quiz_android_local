package com.whp.quizlocal.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.whp.quizlocal.R
import com.whp.quizlocal.sound.SoundManager


class SplashScreenActivity : AppCompatActivity() {

    protected var SPLASH_SCREEN_DELAY = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init ()

    }


    private fun init () {

        setContentView(R.layout.splash_screen_layout)

        SoundManager.init(this)
        //SoundManager.playSound(this, SoundManager.SOUND_CORRECT)


        Handler().postDelayed(
            {
                // This method will be executed once the timer is over
                switchToNextActivity()
            },
            SPLASH_SCREEN_DELAY // value in milliseconds
        )
    }

    private fun switchToNextActivity() {
        val intent = Intent(this, GameListActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_out)
        finish()
    }

}