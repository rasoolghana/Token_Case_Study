package com.rghapp.tokencasestudy.data.network


object NetworkConfig {
    private const val ROOT_URL: String = "https://sandbox-api.payosy.com"
    const val TIME_OUT: Long = 20
    const val BASE_URL: String = "$ROOT_URL/api/"
    const val RETRY_COUNT = 1
}