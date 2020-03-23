package com.whp.quizlocal.graphic

import android.R
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable


class DrawableUtils {

    companion object Static {

        fun setColor (drawable : Drawable, color : String) {

            if (drawable is ShapeDrawable) {
                // cast to 'ShapeDrawable'
                val shapeDrawable = drawable as ShapeDrawable
                shapeDrawable.paint.color = Color.parseColor(color)
            } else if (drawable is GradientDrawable) {
                // cast to 'GradientDrawable'
                val gradientDrawable = drawable as GradientDrawable
                gradientDrawable.setColor(Color.parseColor(color))
            } else if (drawable is ColorDrawable) {
                // alpha value may need to be set again after this call
                val colorDrawable = drawable as ColorDrawable
                colorDrawable.color = Color.parseColor(color)
            }

        }

    }

}