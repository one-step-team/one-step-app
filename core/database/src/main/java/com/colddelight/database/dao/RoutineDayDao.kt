package com.colddelight.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.MapColumn
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.colddelight.database.model.RoutineDayEntity
import com.colddelight.database.model.RoutineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDayDao {


    @Query("SELECT * FROM routine_day WHERE routine_id=(:routineId)")
    fun getAllRoutineDay(routineId: Int): Flow<List<RoutineDayEntity>>
    @Query("SELECT DISTINCT routine_day.* FROM routine_day INNER JOIN day_exercise ON routine_day.id = day_exercise.routine_day_id WHERE routine_day.routine_id = :routineId")
    fun getAllWorkDay(routineId: Int): Flow<List<RoutineDayEntity>>

    @Query("SELECT * FROM routine_day WHERE day_of_week=(:dayOfWeek)")
    fun getTodayRoutineDay(dayOfWeek: Int): Flow<RoutineDayEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRoutineDay(routineDay: RoutineDayEntity)

    @Transaction
    suspend fun deleteRoutineDayAndRelatedData(routineDayId: Int) {
        deleteDayExercisesByRoutineDayId(routineDayId)
        deleteRoutineDayById(routineDayId)
    }

    @Query("DELETE FROM routine_day WHERE id = :routineDayId")
    suspend fun deleteRoutineDayById(routineDayId: Int)

    @Query("DELETE FROM day_exercise WHERE routine_day_id = :routineDayId")
    suspend fun deleteDayExercisesByRoutineDayId(routineDayId: Int)

}