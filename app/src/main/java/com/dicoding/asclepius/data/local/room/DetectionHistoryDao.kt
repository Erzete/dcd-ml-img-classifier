package com.dicoding.asclepius.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dicoding.asclepius.data.local.entity.DetectionHistory

@Dao
interface DetectionHistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(newHistory: DetectionHistory)

    @Delete
    fun delete(newHistory: DetectionHistory)

    @Query("SELECT * from detectionhistory")
    fun getHistory(): LiveData<List<DetectionHistory>>

}