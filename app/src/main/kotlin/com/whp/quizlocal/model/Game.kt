package com.whp.quizlocal.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game (

    val code: String,
    val name : String,
    val description : String,
    val color : String,
    val quizzes : ArrayList<Quiz>

) : Parcelable