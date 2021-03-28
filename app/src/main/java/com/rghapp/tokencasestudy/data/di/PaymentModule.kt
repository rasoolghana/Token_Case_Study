package com.rghapp.tokencasestudy.data.di

import com.rghapp.tokencasestudy.data.repository.PaymentRepositoryImpl
import com.rghapp.tokencasestudy.data.serviceinterfaces.PaymentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Rasool Ghana on 3/28/21.
 * Email : Rasool.ghana@gmail.com
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class PaymentModule {

    @Binds
    abstract fun bindRepository(repository: PaymentRepositoryImpl):
            PaymentRepository

}