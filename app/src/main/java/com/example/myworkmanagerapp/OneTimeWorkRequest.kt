package com.example.myworkmanagerapp

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.StringBuilder

class OneTimeWorkRequest(val c: Context, workerParameters: WorkerParameters) :
    Worker(c, workerParameters) {
    override fun doWork(): Result {
        Utils.setString(
            c,
            "OneTimeWorkRequest",
            "${Utils.getString(c, "OneTimeWorkRequest")}${Utils.getTimeInString()}${System.lineSeparator()}"
        )
        return Result.success()
    }
}