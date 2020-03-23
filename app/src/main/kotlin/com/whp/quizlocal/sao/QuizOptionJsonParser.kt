package com.whp.quizlocal.sao

import com.whp.quizlocal.model.QuizOption
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class QuizOptionJsonParser {

    companion object Static {

        @Throws(JSONException::class)
        fun getOptions (jsonOptions : JSONArray) : ArrayList<QuizOption> {

            var options : ArrayList<QuizOption> = arrayListOf<QuizOption>()

            for (i in 0 until jsonOptions.length()) {
                val option: QuizOption = getOption(jsonOptions.get(i) as JSONObject)
                options.add(option)
            }

            return options
        }

        @Throws(JSONException::class)
        fun getOption(jsonObject: JSONObject) : QuizOption {

            val code : String = jsonObject.getString("code")
            val description : String = jsonObject.getString("description")

            val option = QuizOption(code, description)

            return option
        }


    }

}