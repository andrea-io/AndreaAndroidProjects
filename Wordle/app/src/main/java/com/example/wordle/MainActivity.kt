package com.example.wordle

import FourLetterWordList
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private var guessesRemaining: Int = 3;
    private var wordToGuess = FourLetterWordList.getRandomFourLetterWord();
    private var guessCount = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var guessButton = findViewById<Button>(R.id.buttonGuess);
        var guessInput = findViewById<EditText>(R.id.editTextGuess);

        guessButton.setOnClickListener {
            val guess = guessInput.text.toString().uppercase()
            val result = checkGuess(guess)
            guessCount += 1
            val nextVal = guessCount + 1;

            val textView = findViewById<TextView>(resources.getIdentifier("textView$guessCount", "id", packageName))
            val checkView = findViewById<TextView>(resources.getIdentifier("textView$guessCount", "id", packageName))

            guessesRemaining -= 1
            evaluate(guessCount, guess, result, guessesRemaining, textView, checkView, findViewById(R.id.textView7));

            guessInput.text.clear()
        }
    }

    private fun evaluate(guessCount: Int, guess: String, result: String, guessesRemaining: Int, textViewGuessAppend: TextView, textViewCheckAppend: TextView, finalWord: TextView) {
        textViewGuessAppend.append("Guess #" + guessCount + " " + guess +"\n")
        textViewCheckAppend.append("Guess #" + guessCount + " Check " + result + "\n")

        if (result == "OOOO") {
            Toast.makeText(applicationContext, "You've guessed correctly", Toast.LENGTH_SHORT).show()
            finalWord.append(wordToGuess)
        } else if (guessesRemaining == 0) {
            Toast.makeText(applicationContext, "You've exceeded your number of guesses", Toast.LENGTH_SHORT).show()
            finalWord.append(wordToGuess)
        } else {

        }
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */

    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}