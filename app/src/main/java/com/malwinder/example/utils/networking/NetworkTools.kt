package com.malwinder.example.utils.networking

import com.malwinder.example.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkTools {
    val retrofit : Retrofit
    init {
        var okhttpClient = OkHttpClient.Builder()
                .addInterceptor(
                        HttpLoggingInterceptor()
                                .setLevel(
                                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                        else HttpLoggingInterceptor.Level.BASIC)
                )
                .build()

        retrofit = Retrofit.Builder()
                .client(okhttpClient)
                .baseUrl(BuildConfig.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}