package com.whp.quizlocal.adapter

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.whp.quizlocal.R
import com.whp.quizlocal.model.Game
import kotlinx.android.synthetic.main.game_list_item.view.*

class GameListAdapter (private val games: ArrayList<Game>, private val listener : OnItemClickListener) : RecyclerView.Adapter <GameListAdapter.GameListHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameListAdapter.GameListHolder {

        val inflatedView = parent.inflate(R.layout.game_list_item, false)
        return GameListHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: GameListAdapter.GameListHolder, position: Int) {

        val itemGame = games[position]
        holder.bindGame(itemGame)
        holder.itemView.setOnClickListener {
            listener.onItemClick(games[position])
        }

    }


    //
    class GameListHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v
        private var game: Game? = null


        fun bindGame(game: Game) {
            this.game = game

            (view.game_item_background as CardView).setCardBackgroundColor(Color.parseColor(game.color))

            view.game_item_description.text = game.name
        }

    }

    interface OnItemClickListener {
        fun onItemClick(game : Game)
    }

}