package com.ismaildev.myapplication2023.data.retrofit

import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {
    private fun getRetrofit(): Retrofit {
        val interceptor = run {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        val baseUrl = "https://dummyapi.online/api/"
        val gembokName = "name"
        val gembokKey = "key"

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .addHeader(gembokName, gembokKey)
                    .build()
                chain.proceed(request)
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService(): Endpoint {
        return getRetrofit().create(Endpoint::class.java)
    }
}