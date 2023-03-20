package com.example.lesson_coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.lesson_coroutines.ui.theme.LessonCoroutinesTheme
import com.example.lesson_coroutines.view.AppScreen
import com.example.lesson_coroutines.viewModel.AppScreenViewModel

class MainActivity : ComponentActivity() {
    private lateinit var appScreenViewModel: AppScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val provider = ViewModelProvider(this)
        appScreenViewModel = provider[AppScreenViewModel::class.java]

        setContent {
            LessonCoroutinesTheme {
                AppScreen()
            }
        }
    }
}