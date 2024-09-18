package com.aura.test.vromanovich.domain.usecase.boots.last.set

interface SetLastBootTimestampUseCase {

    suspend operator fun invoke(timestamp: Long)
}