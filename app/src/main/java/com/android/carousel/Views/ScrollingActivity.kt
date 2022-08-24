package com.android.carousel.Views

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.android.carousel.Adapter.ChildAdapter
import com.android.carousel.Adapter.ViewPagerAdapter
import com.android.carousel.DataClasses.Item
import com.android.carousel.ViewModels.CarouselViewModel
import com.android.carousel.databinding.ActivityScrollingBinding

class ScrollingActivity : AppCompatActivity() {
    private lateinit var carouselViewModel: CarouselViewModel
    private lateinit var indexList: MutableList<String>
    private lateinit var dummy: MutableList<String>
    private lateinit var binding: ActivityScrollingBinding
    private var index: Int = 0
    private lateinit var adapter: ChildAdapter
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        carouselViewModel =
            ViewModelProviders.of(this).get(CarouselViewModel::class.java)
        setRecyclerView(index)
        initListener()


        var count: MutableLiveData<List<Item>> = carouselViewModel.getInitialList(this)
        count.observe(this, Observer {
            viewPagerAdapter = ViewPagerAdapter(this@ScrollingActivity, it)
            binding.viewPager.adapter = viewPagerAdapter
            binding.tabLayout.setupWithViewPager(binding.viewPager,true)

        })
    }

    private fun initListener() {
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                binding.etSearch.setText("")
                setRecyclerView(position)

            }

        })
        carouselViewModel.addFilter(binding.etSearch).
        observe(this , Observer {
            if (it != null) {
                filter(it)
            }
        })
    }


    private fun setRecyclerView(index: Int) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        indexList =
            carouselViewModel.getDataByIndex(this, index)
        adapter = ChildAdapter(indexList)
        binding.recyclerView.adapter = adapter
    }

    private fun filter(text: String) {
        dummy = ArrayList<String>()
        for (i in 0..indexList.size - 1) {
            if (indexList.get(i).toLowerCase().contains(text.toLowerCase())) {
                dummy.add(indexList.get(i))
            }
        }
        refreshData(dummy)
    }

    private fun refreshData(list: MutableList<String>) {
        adapter = ChildAdapter(list)
        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}