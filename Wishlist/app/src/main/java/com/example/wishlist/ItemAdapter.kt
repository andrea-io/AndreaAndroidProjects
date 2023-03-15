package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter (var items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTextView : TextView
        val itemURLTextView : TextView
        val itemPriceTextView : TextView

        init {
            // Store each of the layout's views into
            // the public final member variables created above
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemURLTextView = itemView.findViewById(R.id.itemURLTextView);
            itemPriceTextView = itemView.findViewById(R.id.itemPriceTextView);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.product_item, parent, false)

        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Populate data into the item through the holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val product = items[position]

        // Set item views based on views and data model
        holder.itemNameTextView.text = product.productName
        holder.itemURLTextView.text = product.productURL
        holder.itemPriceTextView.text = product.productPrice
    }

    fun update(newWishListModel: List<Item>) {
        items = newWishListModel
    }

    override fun getItemCount(): Int {
        return items.size
    }
}