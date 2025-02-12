package com.example.countdowntimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import androidx.work.workDataOf
import com.example.countdowntimer.ui.theme.CountdownTimerTheme

class MainActivity : ComponentActivity() {
    private val timerViewModel = TimerViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountdownTimerTheme {
                TimerScreen(timerViewModel = timerViewModel)
            }
        }
    }

    override fun onStop() {
        super.onStop()

        // Start TimerWorker if the timer is running
        if (timerViewModel.isRunning) {
            startWorker(timerViewModel.remainingMillis)
        }
    }

    private fun startWorker(millisRemain: Long) {
        val timerWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<TimerWorker>()
            .setInputData(
                workDataOf(
                    KEY_MILLIS_REMAINING to millisRemain
                )
            ).build()

        WorkManager.getInstance(applicationContext).enqueue(timerWorkRequest)
    }
}