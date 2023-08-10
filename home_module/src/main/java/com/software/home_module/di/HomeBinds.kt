package com.software.home_module.di

import com.software.home_module.data.datasource.api.HomeApiImpl
import com.software.home_module.data.repository.HomeRepositoryImpl
import com.software.home_module_api.data.datasource.api.HomeApi
import com.software.home_module_api.data.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeBinds {
    @Binds
    abstract fun bindHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindHomeApi(homeApiImpl: HomeApiImpl): HomeApi
}