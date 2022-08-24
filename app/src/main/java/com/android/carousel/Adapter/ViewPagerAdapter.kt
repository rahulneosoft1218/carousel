package com.android.carousel.Adapter


import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.android.carousel.DataClasses.Item
import com.android.carousel.databinding.ListItemBinding

class ViewPagerAdapter(val context: Context, val imageList: List<Item>?) : PagerAdapter() {
    private lateinit var binding: ListItemBinding
    override fun getCount(): Int {
        return imageList?.size ?:0
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding = ListItemBinding.inflate(LayoutInflater.from(context), container, false)
        imageList?.get(position)?.icon?.let { binding.listItemIcon.setImageResource(it) }
        binding.listItemText.setText(imageList?.get(position)?.title)
        container?.addView(binding.root)
        return binding.root
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}
