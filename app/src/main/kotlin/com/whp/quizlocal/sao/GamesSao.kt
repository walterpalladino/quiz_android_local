package com.whp.quizlocal.sao

import android.content.Context
import android.util.Log
import com.whp.quizlocal.model.Game
import org.json.JSONException
import java.io.InputStream

class GamesSao {


    companion object Static {

        private var TAG : String = "GamesSao"

        @Throws(JSONException::class)
        fun getAll (context: Context) : ArrayList<Game> {
            var games : ArrayList<Game>
            val inputString : String

            games = arrayListOf<Game>()

            //  Read the local file from the assets folder
            try {
                val inputStream:InputStream = context.assets.open("gamelist.json")
                inputString = inputStream.bufferedReader().use{it.readText()}
                Log.d(TAG,inputString)
            } catch (e:Exception){
                Log.d(TAG, e.toString())
                return games
            }

            //  Parse it
            games = GameGetAllJsonParser.parse  (inputString)


            return games
        }

    }

}