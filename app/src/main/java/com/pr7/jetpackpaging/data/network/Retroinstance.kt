package com.pr7.jetpackpaging.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retroinstance {


    fun getRetrofitInstance():Retrofit{
        val retrofit=Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

}