package com.whp.quizlocal.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.whp.quizlocal.R
import com.whp.quizlocal.animation.BounceInterpolator
import com.whp.quizlocal.model.Game
import com.whp.quizlocal.service.GamesService
import kotlinx.android.synthetic.main.game_results.*

class GameResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        init ()
    }

    private fun init() {

        setContentView(R.layout.game_results)

        val game : Game = GamesService.actualGame!!

        game_results_game_name.text = game.name

        //  Inform results
        val okCount : Int = GamesService.getCorrectAnswersCount()
        val wrongCount = GamesService.quizzes.size - okCount
        game_results_answers_correct.text = okCount.toString()
        game_results_answers_wrong.text = wrongCount.toString()


        val okAnim = AnimationUtils.loadAnimation(this, R.anim.bounce)

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        val okInterpolator = BounceInterpolator(0.2, 20.0)
        okAnim.interpolator = okInterpolator
        okAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                GamesService.generateQuizzes()
                switchToGameList ()
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })

        action_ok.setOnClickListener() { it: View? ->

            action_ok!!.startAnimation(okAnim)

        }

    }

    private fun switchToGameList () {

        val intent = Intent(this, GameListActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.left_to_right_in, R.anim.left_to_right_out)
        finish()

    }

}
