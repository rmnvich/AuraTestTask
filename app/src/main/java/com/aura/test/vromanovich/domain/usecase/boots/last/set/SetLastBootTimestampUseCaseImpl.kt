package com.aura.test.vromanovich.domain.usecase.boots.last.set

import com.aura.test.vromanovich.domain.repository.BootsRepository
import javax.inject.Inject

class SetLastBootTimestampUseCaseImpl @Inject constructor(
    private val repository: BootsRepository,
) : SetLastBootTimestampUseCase {

    override suspend fun invoke(timestamp: Long) {
        repository.setLastBootTime(timestamp)
    }
}