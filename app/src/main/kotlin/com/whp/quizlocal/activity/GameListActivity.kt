package com.whp.quizlocal.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.whp.quizlocal.R
import com.whp.quizlocal.adapter.GameListAdapter
import com.whp.quizlocal.model.Game
import com.whp.quizlocal.service.GamesService
import kotlinx.android.synthetic.main.game_list.*

class GameListActivity : AppCompatActivity() {

    private var gameList: ArrayList<Game> = ArrayList()

    private lateinit var gameListAdapter: GameListAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init ()
    }

    private fun init () {

        setContentView(R.layout.game_list)

        linearLayoutManager = LinearLayoutManager(this)
        game_list_view.layoutManager = linearLayoutManager

        gameListAdapter = GameListAdapter(gameList,
            object : GameListAdapter.OnItemClickListener {
                override fun onItemClick(game: Game) {

                    gameSelected (game)

                }
            })
        game_list_view.adapter = gameListAdapter

    }


    override fun onStart() {

        super.onStart()
        if (gameList.size == 0) {
            requestGames()
        }

    }


    private fun requestGames() {

        gameList.clear()
        gameList.addAll( GamesService.getAll(this) )

        gameListAdapter.notifyDataSetChanged()
    }

    private fun gameSelected (game : Game) {
        GamesService.actualGame = game

        val intent = Intent(this, GameBriefActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.right_to_left_in, R.anim.right_to_left_out)
        finish()

    }
}
