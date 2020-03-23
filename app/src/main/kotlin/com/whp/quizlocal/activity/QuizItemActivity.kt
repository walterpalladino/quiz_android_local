package com.whp.quizlocal.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.whp.quizlocal.R
import com.whp.quizlocal.sao.GamesSao

class QuizItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GamesSao.getAll(this)
    }
}
