package com.whp.quizlocal.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Quiz (

    val code : String,
    val description : String,
    val options : ArrayList<QuizOption>,
    val validQuizOptionCode : String,
    val validQuizAnswer : String

) : Parcelable
