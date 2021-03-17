package com.rysanek.currencyconverter.data.remote.models

data class ExchangeResponse (
    val base: String,
    val date: String,
    val rates:Rates
    )