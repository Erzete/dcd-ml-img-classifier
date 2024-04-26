package com.dicoding.asclepius.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.asclepius.data.local.entity.DetectionHistory

@Database(entities = [DetectionHistory::class], version = 1)
abstract class DetectionHistoryDatabase : RoomDatabase() {
    abstract fun detectionHistoryDao(): DetectionHistoryDao
    companion object {
        @Volatile
        private var INSTANCE: DetectionHistoryDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): DetectionHistoryDatabase {
            if (INSTANCE == null) {
                synchronized(DetectionHistoryDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        DetectionHistoryDatabase::class.java, "history_database")
                        .build()
                }
            }
            return INSTANCE as DetectionHistoryDatabase
        }
    }
}