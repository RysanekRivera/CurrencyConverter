package com.rysanek.currencyconverter.data.remote.apis

import com.rysanek.currencyconverter.data.remote.models.ExchangeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeApi {
    
    @GET("/latest")
    suspend fun getRates(
        @Query("base") base: String
    ): Response<ExchangeResponse>
}