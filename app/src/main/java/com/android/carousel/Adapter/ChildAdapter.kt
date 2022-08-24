package com.android.carousel.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.carousel.R
import com.android.carousel.databinding.ListChildBinding

class ChildAdapter(private var mList: MutableList<String>) :
    RecyclerView.Adapter<ChildAdapter.ViewHolder>() {
    lateinit var binding: ListChildBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListChildBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city: String = mList.get(position)
        binding.ivIcon.setImageResource(R.drawable.airplane)
        binding.tvTitle.text = city

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

    }
}