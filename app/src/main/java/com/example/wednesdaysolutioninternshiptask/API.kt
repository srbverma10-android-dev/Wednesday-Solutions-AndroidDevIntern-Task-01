package com.example.wednesdaysolutioninternshiptask

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

interface API {

    @POST("search?")
    fun postTerm(@Query("term") artistName : String) : Call<Item>

    companion object{
        operator fun invoke() : API {
            return Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(API::class.java)
        }
    }

}