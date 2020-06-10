package com.abs.clase14.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abs.clase14.model.Gif
import com.abs.clase14.service.GifServiceType

class GifHistoryViewModel(private val gifService: GifServiceType): ViewModel(){

    lateinit var gifHistory: LiveData<List<Gif>>

    fun bindView(){
        gifHistory = gifService.getGifHistory()
    }
}