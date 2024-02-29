package com.colddelight.data.mapper

import com.colddelight.database.model.DayExerciseEntity
import com.colddelight.database.model.ExerciseEntity
import com.colddelight.model.DayExercise
import com.colddelight.model.DayExerciseUI
import com.colddelight.model.Exercise
import com.colddelight.model.ExerciseCategory
import com.colddelight.model.SetInfo

object DayExerciseEntityMapper {

    fun asEntity(domain: DayExercise): DayExerciseEntity {
        return DayExerciseEntity(
            routineDayId = domain.routineDayId,
            exerciseId = domain.exerciseId,
            kgList = domain.kgList,
            repsList = domain.repsList,
            id = domain.id,
        )
    }

    fun asEntity(domain: List<DayExercise>): List<DayExerciseEntity> {
        return domain.map { routineDay ->
            routineDay.asEntity()
        }
    }




    fun asDomain(entity: Map<DayExerciseEntity, ExerciseEntity>): List<DayExerciseUI> {
        return entity.map {
            DayExerciseUI(
                id = it.key.id,
                routineDayId = it.key.routineDayId,
                exercise = when (it.value.category) {
                    ExerciseCategory.CALISTHENICS ->
                        Exercise.Calisthenics(
                            exerciseId = it.value.id,
                            name = it.value.name,
                            reps = it.key.repsList.maxOrNull() ?: 0,
                            set = it.key.repsList.size,
                            category = it.value.category,
                            setInfoList = it.key.kgList.mapIndexed { index, kg ->
                                SetInfo(
                                    kg,
                                    it.key.repsList[index]
                                )
                            },
                            dayExerciseId = it.key.id

                        )

                    else -> Exercise.Weight(
                        exerciseId = it.value.id,
                        name = it.value.name,
                        min = it.key.kgList.minOrNull() ?: 0,
                        max = it.key.kgList.maxOrNull() ?: 0,
                        category = it.value.category,
                        setInfoList = it.key.kgList.mapIndexed { index, kg ->
                            SetInfo(
                                kg,
                                it.key.repsList[index]
                            )
                        },
                        dayExerciseId = it.key.id

                    )
                },

            )
        }
    }

}

fun List<DayExercise>.asEntity(): List<DayExerciseEntity> {
    return DayExerciseEntityMapper.asEntity(this)
}

fun DayExercise.asEntity(): DayExerciseEntity {
    return DayExerciseEntityMapper.asEntity(this)
}




fun Map<DayExerciseEntity, ExerciseEntity>.asDomain(): List<DayExerciseUI> {
    return DayExerciseEntityMapper.asDomain(this)
}


