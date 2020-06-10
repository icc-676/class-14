package com.abs.clase14.networking

import com.abs.clase14.model.GIFResponse
import retrofit2.Call
import retrofit2.http.GET

interface GifAPI {
    @GET("gifs/random")
    fun getRandomGif(): Call<GIFResponse>
}