package com.dicoding.asclepius.di

import android.content.Context
import com.dicoding.asclepius.data.local.DetectionHistoryRepository
import com.dicoding.asclepius.data.local.room.DetectionHistoryDatabase
import com.dicoding.asclepius.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): DetectionHistoryRepository {
        val database = DetectionHistoryDatabase.getDatabase(context)
        val dao = database.detectionHistoryDao()
        val appExecutors = AppExecutors()
        return DetectionHistoryRepository.getInstance(dao, appExecutors)
    }
}