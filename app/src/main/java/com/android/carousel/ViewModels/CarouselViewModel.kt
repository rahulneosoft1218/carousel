package com.android.carousel.ViewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.android.carousel.DataClasses.Item
import com.android.carousel.R

class CarouselViewModel : ViewModel(){

    private var countLiveData= MutableLiveData<List<Item>>()
    private var filter= MutableLiveData<String>()

    open fun getInitialList(context: Context):MutableLiveData<List<Item>>{
        val possibleItems = listOf(
            Item(context.getString(R.string.west_bengal), R.drawable.bengal),
            Item(context.getString(R.string.kerala), R.drawable.kerala),
            Item(context.getString(R.string.ladakh), R.drawable.ladakh),
            Item(context.getString(R.string.maharashtra), R.drawable.maharashtra),
            Item(context.getString(R.string.meghalaya), R.drawable.meghalaya),
            Item(context.getString(R.string.madhyapradesh), R.drawable.mp),
            Item(context.getString(R.string.punjab), R.drawable.punjab),
            Item(context.getString(R.string.tamilnadu), R.drawable.tamilnadu)
        )

        countLiveData.value= possibleItems
        return countLiveData
    }

    fun getDataByIndex(context: Context, index: Int): MutableList<String> {
        var total = listOf(context.resources.getStringArray(R.array.WestBengal).toMutableList(),
            context.resources.getStringArray(R.array.Kerala).toMutableList(),
            context.resources.getStringArray(R.array.Ladakh).toMutableList(),
            context.resources.getStringArray(R.array.Maharashtra).toMutableList(),
            context.resources.getStringArray(R.array.Meghalaya).toMutableList(),
            context.resources.getStringArray(R.array.MadhyaPradesh).toMutableList(),
            context.resources.getStringArray(R.array.Punjab).toMutableList(),
            context.resources.getStringArray(R.array.TamilNadu).toMutableList(),
        )
        return total.get(index)
    }

    fun addFilter(search: EditText): MutableLiveData<String> {
        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter.value = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(msg: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        return filter
    }

}