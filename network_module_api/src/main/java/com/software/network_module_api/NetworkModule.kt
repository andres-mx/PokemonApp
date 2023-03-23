package com.software.network_module_api

import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface NetworkModule {
    fun networkClient(baseOkHttpClient: OkHttpClient.Builder): OkHttpClient
    fun networkRetrofit(okHttpClient: OkHttpClient): Retrofit
}