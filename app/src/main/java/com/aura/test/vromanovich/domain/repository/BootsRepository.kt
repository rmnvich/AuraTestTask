package com.aura.test.vromanovich.domain.repository

interface BootsRepository {

    suspend fun setLastBootTime(timestamp: Long)

    suspend fun getLastBootTime(): Long
}