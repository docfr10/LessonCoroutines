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
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

@Composable
fun AppScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Fun1()
        //Fun2()
        //Fun3()
        //Fun4()
        //Fun5()
        Fun6()
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

@Composable
fun Fun3() {
    val textList = remember { mutableStateListOf("") }
    LaunchedEffect(Unit) {
        val job = launch(start = CoroutineStart.LAZY) {
            delay(200L)
            textList.add("Start")
            delay(5000L)
        }
        job.start()
        job.join()
        textList.add("Finish")
    }
    textList.add("Other text")
    LazyRow(content = {
        items(textList) {
            Text(text = it)
        }
    })
}

@Composable
fun Fun4() {
    val textList = remember { mutableStateListOf("") }
    LaunchedEffect(Unit) {
        val numDeferred1 = async { 1 + 2 }
        val numDeferred2 = async { 3 + 4 }
        val numDeferred3 = async { 5 + 6 }
        textList.add(numDeferred1.await().toString())
        textList.add(numDeferred2.await().toString())
        textList.add(numDeferred3.await().toString())
    }
    LazyRow(content = {
        items(textList) {
            Text(text = " $it")
        }
    })
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun Fun5() {
    val textList = remember { mutableStateListOf("") }
    LaunchedEffect(Unit) {
        launch(Dispatchers.Default) {
            textList.add(Thread.currentThread().name)
        }
        launch(newSingleThreadContext("Custom Thread")) {
            textList.add(Thread.currentThread().name)
        }
        textList.add(Thread.currentThread().name)
    }
    LazyRow(content = {
        items(textList) {
            Text(text = " $it")
        }
    })
}

@Composable
fun Fun6() {
    val textList = remember { mutableStateListOf("") }
    LaunchedEffect(Unit) {
        val channel = Channel<String>()
        launch {
            val users = listOf("Tom", "Bob", "Sam")
            for (user in users) {
                channel.send(user)  // Отправляем данные в канал
            }
            channel.close()  // Закрытие канала
        }
        for(user in channel) {  // Получаем данные из канала
            textList.add(user)
        }
    }
    LazyRow(content = {
        items(textList) {
            Text(text = " $it")
        }
    })
}