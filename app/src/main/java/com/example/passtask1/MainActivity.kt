package com.example.passtask1

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var count: Int = 0
    private var score: TextView? = null
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get buttons
        val increment: Button = findViewById(R.id.increment)
        val decrement: Button = findViewById(R.id.decrement)
        val reset: Button = findViewById(R.id.reset)

        // set listeners for each button
        increment.setOnClickListener { increment(); playSFX() }
        decrement.setOnClickListener { decrement(); playSFX() }
        reset.setOnClickListener { reset(); playSFX() }

        // get score TextView and initialize it
        score = findViewById(R.id.score)
        score!!.text = count.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("score", count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        count = savedInstanceState.getInt("score")
        score!!.text = count.toString()
    }

    private fun increment() {
        Log.d("", "Increment")

        // increment and clamp such that the value doesn't exceed 15
        count = (++count).coerceAtMost(15)
        score!!.text = count.toString()
    }

    private fun decrement() {
        Log.d("", "Decrement")

        // decrement and clamp such that the value doesn't go below 0
        count = (--count).coerceAtLeast(0)
        score!!.text = count.toString()
    }

    private fun reset() {
        Log.d("", "Reset")
        count = 0
        score!!.text = count.toString()
    }

    private fun playSFX() {
        if (count == 15)
            playVictory()
        else
            playClick()
    }

    private fun playClick() {
        if (mediaPlayer != null)
            mediaPlayer!!.reset()

        mediaPlayer = MediaPlayer.create(this, R.raw.pop)
        mediaPlayer!!.start()
    }

    private fun playVictory() {
        if (mediaPlayer != null)
            mediaPlayer!!.reset()

        Log.d("", "Victory!")
        mediaPlayer = MediaPlayer.create(this, R.raw.success)
        mediaPlayer!!.start()
    }
}