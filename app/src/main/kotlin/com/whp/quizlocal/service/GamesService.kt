package com.whp.quizlocal.service

import android.content.Context
import com.whp.quizlocal.model.Game
import com.whp.quizlocal.model.Quiz
import com.whp.quizlocal.model.QuizOption
import com.whp.quizlocal.sao.GamesSao

class GamesService {

    companion object Static {

        private var TAG: String = "GamesService"

        var actualGame : Game? = null
        var quizzes : ArrayList<Quiz> = ArrayList<Quiz>()
        var answers : ArrayList<Boolean> = ArrayList<Boolean>()
        var actualQuizIdx : Int = -1


        fun getAll(context: Context): ArrayList<Game> {
            return GamesSao.getAll(context)
        }

        fun getActualQuiz () : Quiz? {
            return actualGame?.quizzes?.get(this!!.actualQuizIdx!!)
        }

        fun generateQuizzes () {

            quizzes = actualGame!!.quizzes
            actualQuizIdx = 0
            answers = generateAnswers(quizzes.size)

        }

        fun generateAnswers(size: Int): ArrayList<Boolean> {
            answers = ArrayList<Boolean>()
            for (a in 0 until size) {
                answers.add(false)
            }
            return answers
        }


        fun checkValidAnswer (quizOption: QuizOption) : Boolean {
            if (actualQuizIdx >= 0 && actualQuizIdx < quizzes.size) {
                return (quizzes[actualQuizIdx].validQuizOptionCode == quizOption.code)
            } else {
                return false
            }
        }

        fun checkValidAnswer (quiz: Quiz?, quizOption: QuizOption) : Boolean {
            if (quiz != null) {
                return (quiz.validQuizOptionCode == quizOption.code)
            } else {
                return false
            }
        }

        fun updateQuizStatus (quizOption : QuizOption) {
            answers?.set(actualQuizIdx, checkValidAnswer(getActualQuiz(), quizOption))
        }

        fun updateQuizStatus (result : Boolean) {
            answers?.set(actualQuizIdx, result)
        }

        fun moveNextQuiz () {
            actualQuizIdx += 1
        }

        fun isAnyQuizPending () : Boolean {
            return (actualQuizIdx < quizzes!!.size)
        }

        fun getCorrectAnswersCount () : Int {
            var countCorrectAnswers : Int = 0;
            for (answer in answers) {
                if (answer) {
                    countCorrectAnswers ++;
                }
            }
            return countCorrectAnswers;
        }

    }


}