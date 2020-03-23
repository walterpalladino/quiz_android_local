package com.whp.quizlocal.sao

import com.whp.quizlocal.model.Quiz
import com.whp.quizlocal.model.QuizOption
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class QuizJsonParser {

    companion object Static {

        @Throws(JSONException::class)
        fun getQuizzes (jsonQuizzes : JSONArray) : ArrayList<Quiz> {

            var quizzes : ArrayList<Quiz> = arrayListOf<Quiz>()

            for (i in 0 until jsonQuizzes.length()) {
                val quiz: Quiz = getQuiz(jsonQuizzes.get(i) as JSONObject)
                quizzes.add(quiz)
            }

            return quizzes
        }

        @Throws(JSONException::class)
        fun getQuiz(jsonObject: JSONObject) : Quiz {

            val code : String = jsonObject.getString("code")
            val description : String = jsonObject.getString("description")
            //  Get the Options for this Quiz
            var jsonQuizOptions : JSONArray = jsonObject.getJSONArray("options")
            val options : ArrayList<QuizOption> = QuizOptionJsonParser.getOptions (jsonQuizOptions)

            val validQuizOptionCode : String = jsonObject.getString("quiz_option_code")
            val validQuizAnswer : String = jsonObject.getString("quiz_answer")

            val quiz = Quiz(code, description, options, validQuizOptionCode, validQuizAnswer)

            return quiz
        }


    }

}