package com.whp.quizlocal.animation

import android.view.animation.Interpolator

class BounceInterpolator (val amplitude : Double, val frequency : Double) : Interpolator {

    override fun getInterpolation(input: Float): Float {
        return  ((-1 * Math.pow(Math.E, -input/ amplitude) *
                Math.cos(frequency * input) + 1)) .toFloat()
    }
}