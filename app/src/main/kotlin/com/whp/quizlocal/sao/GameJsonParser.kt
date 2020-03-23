package com.whp.quizlocal.sao

import com.whp.quizlocal.model.Game
import com.whp.quizlocal.model.Quiz
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class GameJsonParser {

    companion object Static {

        @Throws(JSONException::class)
        fun getGames (jsonGames : JSONArray) : ArrayList<Game> {

            var games : ArrayList<Game> = arrayListOf<Game>()

            for (i in 0 until jsonGames.length()) {
                val game: Game = GameJsonParser.getGame(jsonGames.get(i) as JSONObject)
                games.add(game)
            }

            return games
        }

        @Throws(JSONException::class)
        fun getGame(jsonObject: JSONObject): Game {

            val code : String = jsonObject.getString("code")
            val name : String = jsonObject.getString("name")
            val description : String = jsonObject.getString("description")
            val color : String = jsonObject.getString("color")
            // Get Quizzes
            var jsonQuizzes : JSONArray = jsonObject.getJSONArray("quizzes")
            val quizzes : ArrayList<Quiz> = QuizJsonParser.getQuizzes (jsonQuizzes)

            val game = Game(code, name, description, color, quizzes)

            return game
        }

    }
}