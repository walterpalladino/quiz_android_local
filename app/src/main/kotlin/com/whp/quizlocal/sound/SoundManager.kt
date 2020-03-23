package com.whp.quizlocal.sound

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.whp.quizlocal.R


class SoundManager {

    companion object Static {

        private var TAG: String = "SoundManager"

        public val SOUND_CORRECT : Int = R.raw.sound_success
        public val SOUND_ERROR : Int = R.raw.sound_error

        private lateinit var soundPool: SoundPool
        private lateinit var soundPoolMap: HashMap<Int, Int>
        private lateinit var streamsMap: HashMap<Int, Int>

        fun init (context : Context) {

            val attributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            soundPool = SoundPool.Builder().setAudioAttributes(attributes).build()

            soundPoolMap = HashMap(2)
            streamsMap = HashMap(2)

            soundPoolMap.put(SOUND_CORRECT, soundPool.load(context, R.raw.sound_success, 1));
            soundPoolMap.put(SOUND_ERROR, soundPool.load(context, R.raw.sound_error, 1));

        }

        fun playSound(context: Context, soundId: Int) {
            if (soundPool == null || soundPoolMap == null) {
                init(context)
            }

            // Range 0.0 to 1.0
            val volume = 1.0f

            // Play sound with same right and left volume, with a priority of 1,
            // zero repeats (i.e play once), and a playback rate of 1f
            streamsMap[soundId] =
                soundPool.play(soundPoolMap[soundId] as Int, volume, volume, 1, 0, 1f)
        }

        fun stopPlaying(soundId: Int) {
            if (soundPool == null || soundPoolMap == null) {
                return
            }
            soundPool.stop(streamsMap[soundId]!!)
        }

    }
}