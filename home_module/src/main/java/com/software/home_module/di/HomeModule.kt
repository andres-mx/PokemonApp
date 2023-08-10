package com.software.home_module.di

import com.software.home_module.data.datasource.api.HomeService
import com.software.network_module_api.NetworkRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {
    @Provides
    fun provideHomeService(@NetworkRetrofit retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)
}