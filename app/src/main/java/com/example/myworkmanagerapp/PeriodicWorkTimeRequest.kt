package com.example.myworkmanagerapp

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.common.util.concurrent.ListenableFuture
import java.lang.StringBuilder
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class PeriodicWorkTimeRequest(val c: Context, workerParameters: WorkerParameters) :
    Worker(c, workerParameters) {
    override fun doWork(): Result {
        Utils.setString(
            c,
            "PeriodicWorkTimeRequest",
            "${Utils.getString(c, "PeriodicWorkTimeRequest")}${Utils.getTimeInString()}\n"
        )
        return Result.success()
    }
}