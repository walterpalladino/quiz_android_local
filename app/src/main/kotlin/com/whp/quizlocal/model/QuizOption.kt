package com.whp.quizlocal.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuizOption (

    val code : String,
    val description : String

) : Parcelable
