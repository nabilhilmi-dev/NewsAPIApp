package com.nabil.newsapi.service

import com.nabil.newsapi.model.ResponseNews
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers


object RetrofitBuilder{
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getService() = retrofit.create(TopHeadlines::class.java)

}

interface TopHeadlines{
    @Headers("Authorization: 71e9df369e014a90abe4bdb322b9bc59")
    @GET("/v2/top-headlines?country=id")
    fun fetchHeadlines():Call<ResponseNews>
}