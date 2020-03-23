package com.whp.quizlocal.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.whp.quizlocal.R
import com.whp.quizlocal.adapter.QuizOptionListAdapter
import com.whp.quizlocal.adapter.QuizOptionListAdapter.OnItemClickListener
import com.whp.quizlocal.model.Quiz
import com.whp.quizlocal.model.QuizOption
import com.whp.quizlocal.service.GamesService
import kotlinx.android.synthetic.main.game_quiz.*

class QuizActivity : AppCompatActivity() {

    private var quizOptionList: ArrayList<QuizOption> = ArrayList()

    private lateinit var quizOptionListAdapter: QuizOptionListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init ()
    }

    private fun init () {

        setContentView(R.layout.game_quiz)

        initQuiz()
    }

    private fun initQuiz() {

        quiz_option_wrong.visibility = View.INVISIBLE
        quiz_option_correct.visibility = View.INVISIBLE

        val quiz : Quiz = GamesService.getActualQuiz()!!
        quizOptionList = quiz.options

        //  Quiz Status
        quiz_status.text = String.format(resources.getString(R.string.quiz_status_text), (GamesService.actualQuizIdx + 1), GamesService.quizzes.size)

        //  Quiz Description
        quiz_description.text = quiz.description

        linearLayoutManager = LinearLayoutManager(this)
        quiz_option_list_view.layoutManager = linearLayoutManager

        quizOptionListAdapter = QuizOptionListAdapter(quizOptionList,
            object : OnItemClickListener {
                override fun onItemClick(quizOption: QuizOption) {

                    checkAnswer (quizOption)

                }
            })

        quiz_option_list_view.adapter = quizOptionListAdapter

    }

    private fun checkAnswer(quizOption: QuizOption) {

        val result : Boolean = GamesService.checkValidAnswer(quizOption)
        GamesService.updateQuizStatus(result)

        //  Display Result on screen
        if (result) {
            correctAnswer()
        } else {
            wrongAnswer()
        }

        //  Move to the next Quiz or the Results Activity
        moveToNextQuiz()

    }

    private fun wrongAnswer() {
    }

    private fun correctAnswer() {
    }

    private fun moveToNextQuiz () {

        GamesService.moveNextQuiz()

        if (GamesService.isAnyQuizPending()) {

            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_out)
            finish()

        } else {

            val intent = Intent(this, GameResultsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_out)
            finish()

        }
    }
}
