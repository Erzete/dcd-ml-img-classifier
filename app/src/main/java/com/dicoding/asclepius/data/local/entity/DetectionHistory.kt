package com.dicoding.asclepius.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DetectionHistory(
    @PrimaryKey(autoGenerate = false)
    var imageUri: String = "",
    var label: String? = "",
    var score: Int? = 0,
)