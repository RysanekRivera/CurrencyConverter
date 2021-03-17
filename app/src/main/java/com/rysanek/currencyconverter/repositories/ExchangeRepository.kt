package com.rysanek.currencyconverter.repositories

import com.rysanek.currencyconverter.data.remote.apis.ExchangeApi
import com.rysanek.currencyconverter.data.remote.models.ExchangeResponse
import com.rysanek.currencyconverter.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class ExchangeRepository @Inject constructor(
    private val api: ExchangeApi
) {
    suspend fun getRates(base: String): Resource<ExchangeResponse>{
        return try {
            val response = api.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch(e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
    
}