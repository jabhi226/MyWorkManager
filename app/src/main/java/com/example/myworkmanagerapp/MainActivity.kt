package com.example.myworkmanagerapp

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.preference.PreferenceActivity
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private var adapter: PeriodicWorkMangerLogs? = null
    private var preferences: SharedPreferences? = null

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
        println("$key | ${sharedPreferences}")
        findViewById<TextView>(R.id.date_text).text = sharedPreferences.getString(key, "sharedPreferences")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences!!.registerOnSharedPreferenceChangeListener(listener);

        findViewById<Button>(R.id.periodic_work).setOnClickListener {
            val periodicWorkRequest =
                PeriodicWorkRequestBuilder<PeriodicWorkTimeRequest>(
                    20,
                    TimeUnit.MINUTES
                ).addTag("PeriodicWorkTimeRequest").build()

            WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "PeriodicWorkTimeRequest",
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest
            )
        }

        findViewById<Button>(R.id.one_time_work).setOnClickListener {
            Utils.setString(this, "test", "test")
            val oneTimeWorkRequest =
                OneTimeWorkRequestBuilder<OneTimeWorkRequest>().addTag("OneTimeWorkRequest").build()

            WorkManager.getInstance(this).enqueueUniqueWork(
                "OneTimeWorkRequest",
                ExistingWorkPolicy.KEEP,
                oneTimeWorkRequest
            )
        }

        findViewById<Button>(R.id.cancel_all_works).setOnClickListener {
            WorkManager.getInstance(this).cancelAllWork()
        }

        findViewById<RecyclerView>(R.id.rv_periodic_wm).apply {
            this@MainActivity.adapter = PeriodicWorkMangerLogs()
            adapter = this@MainActivity.adapter
            layoutManager = GridLayoutManager(this.context, 2, GridLayoutManager.VERTICAL, true)
            getRefreshedData()
        }
    }

    private fun getRefreshedData(){
        val timeString = Utils.getString(this, "PeriodicWorkTimeRequest")
        val time = timeString.split("\n")
        time.forEach {
            println(it)
        }
        adapter?.submitList(time)
    }

    override fun onPause() {
        super.onPause()
        preferences?.unregisterOnSharedPreferenceChangeListener(listener)
    }

    override fun onResume() {
        super.onResume()
        preferences?.registerOnSharedPreferenceChangeListener(listener)
    }

}