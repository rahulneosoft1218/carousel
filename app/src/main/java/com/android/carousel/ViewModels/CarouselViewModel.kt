package com.android.carousel.ViewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.android.carousel.DataClasses.Item
import com.android.carousel.R

class CarouselViewModel : ViewModel(){

    private var countLiveData= MutableLiveData<List<Item>>()

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
        var list: MutableList<String> =
            (context.resources.getStringArray(R.array.WestBengal).toMutableList())
        when(index){
             0 ->
                list = (context.resources.getStringArray(R.array.WestBengal).toMutableList())
             1 ->
                list = (context.resources.getStringArray(R.array.Kerala).toMutableList())
             2 ->
                list = (context.resources.getStringArray(R.array.Ladakh).toMutableList())
             3 ->
                list = (context.resources.getStringArray(R.array.Maharashtra).toMutableList())
             4 ->
                list = (context.resources.getStringArray(R.array.Meghalaya).toMutableList())
             5 ->
                list = (context.resources.getStringArray(R.array.MadhyaPradesh).toMutableList())
             6 ->
                list = (context.resources.getStringArray(R.array.Punjab).toMutableList())
             7->
                list = (context.resources.getStringArray(R.array.TamilNadu).toMutableList())
        }
        return list
    }

}