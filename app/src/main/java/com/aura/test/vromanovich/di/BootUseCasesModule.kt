package com.aura.test.vromanovich.di

import com.aura.test.vromanovich.domain.usecase.boots.last.get.GetLastBootTimestampUseCase
import com.aura.test.vromanovich.domain.usecase.boots.last.get.GetLastBootTimestampUseCaseImpl
import com.aura.test.vromanovich.domain.usecase.boots.last.set.SetLastBootTimestampUseCase
import com.aura.test.vromanovich.domain.usecase.boots.last.set.SetLastBootTimestampUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//TODO: Better to separate this bindings and use more specific c¬omponent, not Singleton
@Module
@InstallIn(SingletonComponent::class)
interface BootUseCasesModule {

    @Binds
    fun bindSetLastBootTimestampUseCase(impl: SetLastBootTimestampUseCaseImpl): SetLastBootTimestampUseCase

    @Binds
    fun bindGetLastBootTimestampUseCase(impl: GetLastBootTimestampUseCaseImpl): GetLastBootTimestampUseCase
}