package com.rghapp.tokencasestudy.data.di

import com.rghapp.tokencasestudy.data.network.ApiServiceFactory
import com.rghapp.tokencasestudy.data.network.services.PaymentService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Rasool Ghana on 3/28/21.
 * Email : Rasool.ghana@gmail.com
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
class ApiServiceFactoryModule {

    @Provides
    @Reusable
    fun paymentService(apiService: ApiServiceFactory) =
            apiService.create(PaymentService::class.java)

}