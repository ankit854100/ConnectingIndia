package com.leakyquill.bb84.Interface

import com.leakyquill.bb84.Model.Photos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceHolderApi {

    @GET("photos")
    fun getPhotos() : Call<List<Photos>>

}