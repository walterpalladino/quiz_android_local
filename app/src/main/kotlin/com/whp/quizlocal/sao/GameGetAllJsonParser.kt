package com.whp.quizlocal.sao

import com.whp.quizlocal.model.Game
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class GameGetAllJsonParser {

    companion object Static {

        @Throws(JSONException::class)
        fun parse (jsonString : String) : ArrayList<Game> {
            return parse (JSONObject(jsonString))
        }

        @Throws(JSONException::class)
        fun parse (jsonObject : JSONObject) : ArrayList<Game> {

            var jsonGames : JSONArray = jsonObject.getJSONArray("games")

            var games : ArrayList<Game> = GameJsonParser.getGames (jsonGames)

            return games
        }

    }


}
