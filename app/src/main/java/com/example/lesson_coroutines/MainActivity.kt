package com.example.lesson_coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import com.example.lesson_coroutines.ui.theme.LessonCoroutinesTheme
import com.example.lesson_coroutines.view.AppScreen

class MainActivity : ComponentActivity() {
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