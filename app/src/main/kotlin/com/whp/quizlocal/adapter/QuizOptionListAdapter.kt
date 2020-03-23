package com.whp.quizlocal.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.whp.quizlocal.R
import com.whp.quizlocal.model.QuizOption
import kotlinx.android.synthetic.main.quiz_option_list_item.view.*


class QuizOptionListAdapter(private val quizOptions: ArrayList<QuizOption>, private val listener : OnItemClickListener) : RecyclerView.Adapter <QuizOptionListAdapter.QuizOptionListHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : QuizOptionListAdapter.QuizOptionListHolder {

        val inflatedView = parent.inflate(R.layout.quiz_option_list_item, false)
        return QuizOptionListHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return quizOptions.size
    }

    override fun onBindViewHolder(holder: QuizOptionListAdapter.QuizOptionListHolder, position: Int) {

        holder.bindQuizOption(quizOptions[position])
        holder.itemView.setOnClickListener {
            listener.onItemClick(quizOptions[position])
        }
    }


    //
    class QuizOptionListHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v
        private var quizOption: QuizOption? = null

        fun bindQuizOption( quizOption: QuizOption ) {
            this.quizOption = quizOption
            view.quiz_option_item_description.text = quizOption.description
        }

    }

    interface OnItemClickListener {
        fun onItemClick(quizOption : QuizOption)
    }
}