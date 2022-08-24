package com.android.carousel.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.carousel.DataClasses.Item
import com.android.carousel.R
import com.android.carousel.databinding.ListItemBinding

class ItemAdapter(val itemClick: (position: Int, item: Item) -> Unit) :
    RecyclerView.Adapter<ItemViewHolder>() {

    private var items: List<Item> = listOf()
    private lateinit var binding: ListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ItemViewHolder(binding.root)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            itemClick(position, items[position])
        }
    }

    override fun getItemCount() = items.size

    fun setItems(newItems: List<Item>?) {
        items = newItems!!
        notifyDataSetChanged()
    }
}

class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: Item) {
        view.findViewById<ImageView>(R.id.list_item_icon).setImageResource(item.icon)
        view.findViewById<TextView>(R.id.list_item_text).text = "${item.title}"
    }
}