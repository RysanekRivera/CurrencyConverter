package com.rysanek.currencyconverter.di

import com.rysanek.currencyconverter.data.remote.apis.ExchangeApi
import com.rysanek.currencyconverter.repositories.ExchangeRepository
import com.rysanek.currencyconverter.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    @Singleton
    @Provides
    fun provideExchangeApi(): ExchangeApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ExchangeApi::class.java)
    
    @Singleton
    @Provides
    fun provideRepository(api: ExchangeApi) = ExchangeRepository(api)
}