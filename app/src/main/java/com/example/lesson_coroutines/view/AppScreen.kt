package com.example.lesson_coroutines.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@Composable
fun AppScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Fun1()
        // Запуск корутины в основном потоке
        //Fun2()
    }
}

@Composable
fun Fun1() {
    val textList = remember { mutableStateListOf(0) }
    LaunchedEffect(Unit) {
        for (i in 1..5) {
            delay(1000L)
            textList.add(i)
        }
    }
    LazyRow(content = {
        items(textList) {
            Text(text = it.toString())
        }
    })
}

@Composable
fun Fun2() {
    val text = remember { mutableStateOf("") }
    runBlocking {
        val result = async { "Result" }
        delay(5000L)
        text.value = result.await()
    }
    Text(text = text.value)
}
