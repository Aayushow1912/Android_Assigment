package com.example.assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter for RecyclerView
class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private var items: List<Item> = emptyList()

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.item_name)
        val priceTextView: TextView = view.findViewById(R.id.item_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.name
        holder.priceTextView.text = "$${item.price}"
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<Item>) {
        items = newItems
        notifyDataSetChanged()
    }
}
