package com.rghapp.tokencasestudy.data.network.interceptors

import com.rghapp.tokencasestudy.data.network.NetworkConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticatorInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var response: Response? = null
        var responseOK = false
        var triedCount = 0

        request = request.newBuilder().header(
                "x-ibm-client-id",
                "d56a0277-2ee3-4ae5-97c8-467abeda984d"
            ).header(
                "x-ibm-client-secret",
                "tF3mK0qU7bM5kG2oL2kQ8dP1xC5yV6kO6dL4kW5iC4eI3mH7vM"
            )
            .build()

        while ((!responseOK || response == null) && triedCount < NetworkConfig.RETRY_COUNT) {
            try {
                response = chain.proceed(request)
                responseOK = response.isSuccessful
            } catch (e: Exception) { } finally {
                triedCount++
            }
        }
        return response ?: chain.proceed(request)
    }
}
