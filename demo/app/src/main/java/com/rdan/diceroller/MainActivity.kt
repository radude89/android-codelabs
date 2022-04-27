package com.rdan.diceroller

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val luckyNumber = 3
    private var currentNumber: Int? = null

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logging()

        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { onRollDiceAction() }
    }

    private fun logging() {
        Log.e(TAG, "ERROR: a serious error like an app crash")
        Log.w(TAG, "WARN: warns about the potential for serious errors")
        Log.i(TAG, "INFO: reporting technical information, such as an operation succeeding")
        Log.d(TAG, "DEBUG: reporting technical information useful for debugging")
        Log.v(TAG, "VERBOSE: more verbose than DEBUG logs")
    }

    private fun onRollDiceAction() {
        rollDice()
        showDiceImage()
        checkLuckyNumber()
    }

    private fun rollDice() {
        currentNumber = Dice(6).roll()
    }

    private fun showDiceImage() {
        val diceImageView: ImageView = findViewById(R.id.diceImageView)
        diceImageView.contentDescription = currentNumber.toString()

        when (currentNumber) {
            1 -> diceImageView.setImageResource(R.drawable.dice_1)
            2 -> diceImageView.setImageResource(R.drawable.dice_2)
            3 -> diceImageView.setImageResource(R.drawable.dice_3)
            4 -> diceImageView.setImageResource(R.drawable.dice_4)
            5 -> diceImageView.setImageResource(R.drawable.dice_5)
            6 -> diceImageView.setImageResource(R.drawable.dice_6)
        }
    }

    private fun checkLuckyNumber() {
        when (currentNumber) {
            luckyNumber -> showToast()
        }
    }

    private fun showToast(message: String = "You got lucky!") {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}

class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}