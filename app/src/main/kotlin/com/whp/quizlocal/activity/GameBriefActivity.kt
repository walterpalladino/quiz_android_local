package com.whp.quizlocal.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.whp.quizlocal.R
import com.whp.quizlocal.animation.BounceInterpolator
import com.whp.quizlocal.graphic.DrawableUtils
import com.whp.quizlocal.model.Game
import com.whp.quizlocal.service.GamesService
import kotlinx.android.synthetic.main.game_brief.*


class GameBriefActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init ()
    }

    private fun init () {

        setContentView(R.layout.game_brief)

        val game : Game = GamesService.actualGame!!

        game_brief_game_name.text = game.name
        game_brief_game_description.text = game.description

        val contentBackground = game_brief_content_id.background
        DrawableUtils.setColor(contentBackground, game.color)

        val okAnim = AnimationUtils.loadAnimation(this, R.anim.bounce)

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        val okInterpolator = BounceInterpolator(0.2, 20.0)
        okAnim.interpolator = okInterpolator
        okAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                GamesService.generateQuizzes()
                switchToGameQuiz ()
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })

        action_ok.setOnClickListener() { it: View? ->

            action_ok!!.startAnimation(okAnim)

        }


        val cancelAnim = AnimationUtils.loadAnimation(this, R.anim.bounce)

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        val cancelInterpolator = BounceInterpolator(0.2, 20.0)
        cancelAnim.interpolator = cancelInterpolator
        cancelAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {

                switchToGameList()

            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })

        action_cancel.setOnClickListener() { it: View? ->

            action_cancel!!.startAnimation(cancelAnim)

        }

    }

    private fun switchToGameList () {

        val intent = Intent(this, GameListActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out)
        finish()

    }

    private fun switchToGameQuiz () {

        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_out)
        finish()
    }

}
