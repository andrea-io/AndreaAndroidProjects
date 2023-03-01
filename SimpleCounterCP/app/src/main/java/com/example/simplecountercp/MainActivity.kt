package com.example.simplecountercp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<ImageButton>(R.id.button);
        val textView = findViewById<TextView>(R.id.textView);
        var counter = 0;
        val upgradeButton = findViewById<Button>(R.id.upgradeBtn);

        button.setOnClickListener {
            Toast.makeText(it.context, "Clicked button!", Toast.LENGTH_SHORT).show()
            counter += 1;
            textView.text = counter.toString();

            if(counter >= 100) {
                upgradeButton.visibility = View.VISIBLE;
                upgradeButton.setOnClickListener {
                    button.setOnClickListener {
                        counter += 2;
                        textView.text = counter.toString();
                    }
                    upgradeButton.visibility = View.INVISIBLE;
                }
            }
        }
    }
}