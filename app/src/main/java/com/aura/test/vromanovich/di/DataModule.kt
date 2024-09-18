package com.aura.test.vromanovich.di

import com.aura.test.vromanovich.data.repository.BootsRepositoryImpl
import com.aura.test.vromanovich.data.repository.NotificationsRepositoryImpl
import com.aura.test.vromanovich.data.storage.SharedPreferencesStorage
import com.aura.test.vromanovich.data.storage.SharedPreferencesStorageImpl
import com.aura.test.vromanovich.domain.repository.BootsRepository
import com.aura.test.vromanovich.domain.repository.NotificationsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindSharedPreferencesStorage(impl: SharedPreferencesStorageImpl): SharedPreferencesStorage

    @Binds
    fun bindNotificationsRepository(imp: NotificationsRepositoryImpl): NotificationsRepository

    @Binds
    fun bindBootsRepository(imp: BootsRepositoryImpl): BootsRepository
}