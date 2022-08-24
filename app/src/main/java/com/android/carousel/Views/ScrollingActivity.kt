package com.android.carousel.Views

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import com.android.carousel.Adapter.ChildAdapter
import com.android.carousel.Adapter.ItemAdapter
import com.android.carousel.DataClasses.Item
import com.android.carousel.R
import com.android.carousel.ViewModels.CarouselViewModel
import com.android.carousel.databinding.ActivityScrollingBinding

class ScrollingActivity : AppCompatActivity() {
    private lateinit var carouselViewModel: CarouselViewModel
    private lateinit var indexList: MutableList<String>
    private lateinit var dummy: MutableList<String>
    private lateinit var binding: ActivityScrollingBinding
    private var index: Int = 0
    private lateinit var adapter: ChildAdapter
    private val itemAdapter by lazy {
        ItemAdapter { position: Int, item: Item ->
            binding.itemList.smoothScrollToPosition(position)
        }
    }

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
            binding.itemList.initialize(itemAdapter)
            binding.itemList.setViewsToChangeColor(
                listOf(
                    R.id.list_item_background,
                    R.id.list_item_text
                )
            )
            itemAdapter.setItems(it)
        })


        binding.itemList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    index =
                        (binding.itemList.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition();
                    setRecyclerView(index)
                }

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }

    private fun initListener() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(msg: CharSequence?, start: Int, before: Int, count: Int) {
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
        if (dummy.isEmpty()) {
        } else {
            adapter = ChildAdapter(dummy)
            binding.recyclerView.adapter = adapter
        }
    }

}