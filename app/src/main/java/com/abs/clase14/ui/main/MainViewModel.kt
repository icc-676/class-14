package com.abs.clase14.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abs.clase14.model.Gif
import com.abs.clase14.service.GifServiceType

class MainViewModel(private val gifService: GifServiceType) : ViewModel() {

   lateinit var gif :LiveData<Gif>

    fun getNewGif(){
        gifService.fetchRandomGif()
    }

    fun bindView(){
       gif = gifService.getLastGif()
    }

}