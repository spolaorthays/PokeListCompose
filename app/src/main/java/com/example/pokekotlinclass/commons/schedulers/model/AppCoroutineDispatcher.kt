package com.example.pokekotlinclass.commons.schedulers.model

import kotlinx.coroutines.CoroutineDispatcher

data class AppCoroutineDispatcher(
    val mainScheduler: CoroutineDispatcher,
    val ioScheduler: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
)