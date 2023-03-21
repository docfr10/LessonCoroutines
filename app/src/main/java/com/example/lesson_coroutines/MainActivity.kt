package com.example.lesson_coroutines

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import com.example.lesson_coroutines.ui.theme.LessonCoroutinesTheme
import com.example.lesson_coroutines.view.AppScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            LessonCoroutinesTheme {
                AppScreen(context = context)
            }
        }
    }
}