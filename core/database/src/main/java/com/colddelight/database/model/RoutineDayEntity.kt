package com.colddelight.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routine_day")
data class RoutineDayEntity(
    @ColumnInfo(name = "routine_id") val routineId: Int,
    @ColumnInfo(name = "day_of_week") val dayOfWeek: Int,
    @ColumnInfo(name = "category_list") val categoryList: List<Int>,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)




//fun RoutineDayEntity.asNetworkRoutineDay() = NetworkRoutineDay(
//    room_id = id,
//    routine_id = routineId,
//    day_of_week = dayOfWeek,
//    category_list = categoryList,
//)