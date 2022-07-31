package com.example.opgg.utils.viewmodel

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class ScheduleProviderModule{
    @Binds
    @Singleton
    abstract fun bindScheduleProvider(provider: ApplicationSchedulerProvider): ScheduleProvider
}

interface ScheduleProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
}

@Singleton
class ApplicationSchedulerProvider @Inject constructor() : ScheduleProvider {
    override fun io(): Scheduler  = Schedulers.io()
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun computation(): Scheduler  = Schedulers.computation()
}