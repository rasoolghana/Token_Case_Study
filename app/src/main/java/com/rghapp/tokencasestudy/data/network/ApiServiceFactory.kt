package com.rghapp.tokencasestudy.data.network

import com.rghapp.tokencasestudy.BuildConfig
import com.rghapp.tokencasestudy.data.network.NetworkConfig.BASE_URL
import com.rghapp.tokencasestudy.data.network.NetworkConfig.TIME_OUT
import com.rghapp.tokencasestudy.data.network.apiresponse.ApiResponseCallAdapterFactory
import com.rghapp.tokencasestudy.data.network.interceptors.AuthenticatorInterceptor
import com.rghapp.tokencasestudy.data.network.interceptors.HttpLogIntercept
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.net.ssl.*


class ApiServiceFactory @Inject constructor(
		private val httpLogIntercept: HttpLogIntercept,
		private val authenticatorInterceptor: AuthenticatorInterceptor
) {
	fun <T> create(serviceClass: Class<T>): T = retrofit().create(serviceClass)
	
	private fun retrofit(): Retrofit = Retrofit.Builder()
		.baseUrl(BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.addCallAdapterFactory(ApiResponseCallAdapterFactory())
		.client(okHttpClientBuilder().build())
		.build()
	
	private fun okHttpClientBuilder() : OkHttpClient.Builder {
		val trustManager = RGHTrustManager()
		val trustAllCerts: Array<TrustManager> = arrayOf(trustManager)
		val sslContext = SSLContext.getInstance("SSL")
		sslContext.init(null, trustAllCerts, SecureRandom())

		val builder = OkHttpClient.Builder()
			.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
			.writeTimeout(TIME_OUT, TimeUnit.SECONDS)
			.readTimeout(TIME_OUT, TimeUnit.SECONDS)
			.sslSocketFactory(sslContext.socketFactory,trustManager)
			.retryOnConnectionFailure(true)
			.addInterceptor(authenticatorInterceptor)
		if (BuildConfig.DEBUG) {
			builder.addInterceptor(httpLogIntercept.getIntercept())
		}
		return builder
	}

	inner class RGHTrustManager : X509TrustManager {

		override fun checkServerTrusted(
				p0: Array<out X509Certificate>?,
				p1: String?
		) {
		}

		override fun checkClientTrusted(
				p0: Array<out X509Certificate>?,
				p1: String?
		) {
		}

		override fun getAcceptedIssuers(): Array<X509Certificate> {
			return arrayOf()
		}
	}
}