package com.rghapp.tokencasestudy.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Rasool Ghana on 3/28/21.
 * Email : Rasool.ghana@gmail.com
 */
@Module(
    includes = [
        ApiServiceFactoryModule::class,
        PaymentModule::class
    ]
)
@InstallIn(ActivityRetainedComponent::class)
abstract class DataModule