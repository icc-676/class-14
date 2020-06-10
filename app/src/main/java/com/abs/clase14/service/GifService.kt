package com.abs.clase14.service

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.abs.clase14.model.GIFResponse
import com.abs.clase14.model.Gif
import com.abs.clase14.model.GifDao
import com.abs.clase14.networking.GifAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface GifServiceType {
    fun fetchRandomGif()
    fun getLastGif(): LiveData<Gif>
    fun saveGif(gif: Gif)
    fun getGifHistory(): LiveData<List<Gif>>
}

class GifService(private val api: GifAPI, private val database: GifDao) : GifServiceType {

    override fun fetchRandomGif() {
        api.getRandomGif().enqueue(object : Callback<GIFResponse> {
            override fun onResponse(call: Call<GIFResponse>, response: Response<GIFResponse>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val gif = Gif(
                            null,
                            response.body()?.data?.imageUrl ?: ""
                        )
                        saveGif(gif)
                    }
                } else {

                }
            }

            override fun onFailure(call: Call<GIFResponse>, t: Throwable) {
            }
        })
    }

    override fun getLastGif(): LiveData<Gif> {
        return database.getLastGif()
    }

    override fun saveGif(gif: Gif) {
        AsyncTask.execute {
            database.insert(gif)
        }
    }

    override fun getGifHistory(): LiveData<List<Gif>> {
        return database.getAllGifs()
    }

}