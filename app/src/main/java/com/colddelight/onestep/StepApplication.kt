package com.colddelight.onestep

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.colddelight.data.CustomWorker
import com.colddelight.data.repository.ExerciseRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class StepApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CustomWorkerFactory

    override val workManagerConfiguration: Configuration
        get() =
            Configuration.Builder().setMinimumLoggingLevel(Log.DEBUG)
                .setWorkerFactory(workerFactory)
                .build()
}

class CustomWorkerFactory @Inject constructor() :
    WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker = CustomWorker(appContext, workerParameters)

}

