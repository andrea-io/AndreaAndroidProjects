package com.example.wishlist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Getting recycler view
        recyclerView = findViewById(R.id.recyclerView)

        // Default card
        recyclerView.adapter = ItemAdapter(
            listOf(
                Item(productName = "", productURL = "", productPrice = "")))

        //Setting the layout to linear
        recyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.button).setOnClickListener {
            addCard()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addCard() {
        val nameInputText = findViewById<EditText>(R.id.editTextName)
        val priceInputText = findViewById<EditText>(R.id.editTextPrice)
        val urlInputText = findViewById<EditText>(R.id.editTextURL)

        val newItem = Item(nameInputText.text.toString(), urlInputText.text.toString(), priceInputText.text.toString())

        val adapter = recyclerView.adapter as ItemAdapter
        adapter.update(adapter.items + newItem)

        adapter.notifyDataSetChanged()

        nameInputText.setText("")
        priceInputText.setText("")
        urlInputText.setText("")
    }
}