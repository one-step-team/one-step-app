package com.colddelight.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.colddelight.database.model.DayExerciseEntity
import com.colddelight.database.model.ExerciseEntity
import com.colddelight.database.model.HistoryExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryExerciseDao {

    @Query("SELECT * FROM history_exercise JOIN exercise ON history_exercise.exercise_id = exercise.id WHERE history_exercise.history_id=(:historyId)")
    fun getTodayHistoryExercises(historyId: Int): Flow<Map<HistoryExerciseEntity, ExerciseEntity>>

    @Query("SELECT * FROM history_exercise JOIN exercise ON history_exercise.exercise_id = exercise.id WHERE history_exercise.history_id=(:historyId) AND history_exercise.is_done = 1")
    fun getDoneHistoryExercises(historyId: Int): Flow<Map<HistoryExerciseEntity, ExerciseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoryExercise(historyExerciseEntity: HistoryExerciseEntity)

    @Query("UPDATE history_exercise SET kg_list = :kgList WHERE id = :historyExerciseId")
    suspend fun updateKgList(historyExerciseId: Int, kgList: List<Int>)

    @Query("UPDATE history_exercise SET reps_list = :repsList WHERE id = :historyExerciseId")
    suspend fun updateRepsList(historyExerciseId: Int, repsList: List<Int>)

    @Query("UPDATE history_exercise SET kg_list = :kgList, reps_list = :repsList WHERE id = :historyExerciseId")
    suspend fun updateSetInfoList(historyExerciseId: Int, kgList: List<Int>, repsList: List<Int>)


    @Query("UPDATE history_exercise SET is_done = :isDone WHERE id = :id")
    suspend fun updateHistoryExercise(id: Int, isDone: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(historyExercises: List<HistoryExerciseEntity>)

    @Query("DELETE FROM history_exercise WHERE day_exercise_id = :dayExerciseId")
    suspend fun deleteHistoryExerciseById(dayExerciseId: Int)

}