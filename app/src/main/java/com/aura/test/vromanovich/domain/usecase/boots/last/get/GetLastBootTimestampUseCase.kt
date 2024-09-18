package com.aura.test.vromanovich.domain.usecase.boots.last.get

interface GetLastBootTimestampUseCase {

    suspend operator fun invoke(): Long
}