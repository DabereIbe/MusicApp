package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("x-rapidapi-key: ", "x-rapidapi-host: ")
    @GET("search")
    fun getData(@Query("q") query : String) : Call<MyData>

}