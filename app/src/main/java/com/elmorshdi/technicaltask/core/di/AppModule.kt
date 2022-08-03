package com.elmorshdi.technicaltask.core.di

import com.elmorshdi.technicaltask.core.repository.Repository
import com.elmorshdi.technicaltask.data.network.ApiService
import com.elmorshdi.technicaltask.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule {
        @Provides
        @Singleton
        fun provideRetrofit(): ApiService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(ApiService::class.java)
        }

        @Singleton
        @Provides
        fun provideDefaultRepository(
            api: ApiService
        ) = Repository( api) as MainRepository

    }
const val BASE_URL = "https://dummyjson.com"
