package com.example.pokekotlinclass.commons.schedulers.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoSchedulerQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainSchedulerQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ComputationSchedulerQualifier