package com.colddelight.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.colddelight.model.ExerciseCategory

@Entity(tableName = "exercise")
data class ExerciseEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category") val category: ExerciseCategory,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)

