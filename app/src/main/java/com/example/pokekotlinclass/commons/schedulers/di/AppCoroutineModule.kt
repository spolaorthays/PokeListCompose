package com.example.pokekotlinclass.commons.schedulers.di

import com.example.pokekotlinclass.commons.schedulers.model.AppCoroutineDispatcher
import com.example.pokekotlinclass.commons.schedulers.qualifiers.ComputationSchedulerQualifier
import com.example.pokekotlinclass.commons.schedulers.qualifiers.IoSchedulerQualifier
import com.example.pokekotlinclass.commons.schedulers.qualifiers.MainSchedulerQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppCoroutineModule {

    @Singleton
    @Provides
    fun provideSchedulers(
        @MainSchedulerQualifier mainScheduler: CoroutineDispatcher,
        @IoSchedulerQualifier ioScheduler: CoroutineDispatcher,
        @ComputationSchedulerQualifier computationScheduler: CoroutineDispatcher
    ) = AppCoroutineDispatcher(
        mainScheduler = mainScheduler,
        ioScheduler = ioScheduler,
        computation = computationScheduler
    )

    @MainSchedulerQualifier
    @Provides
    fun provideMainThread(): CoroutineDispatcher = Dispatchers.Main

    @IoSchedulerQualifier
    @Provides
    fun provideIoThread(): CoroutineDispatcher = Dispatchers.IO

    @ComputationSchedulerQualifier
    @Provides
    fun provideComputationThread(): CoroutineDispatcher = Dispatchers.Default
}