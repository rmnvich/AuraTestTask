package com.aura.test.vromanovich.domain.usecase.boots.last.get

import com.aura.test.vromanovich.domain.repository.BootsRepository
import javax.inject.Inject

class GetLastBootTimestampUseCaseImpl @Inject constructor(
    private val repository: BootsRepository,
) : GetLastBootTimestampUseCase {

    override suspend fun invoke(): Long = repository.getLastBootTime()
}