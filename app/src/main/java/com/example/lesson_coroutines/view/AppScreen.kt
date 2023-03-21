package com.example.lesson_coroutines.view

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lesson_coroutines.service.MyService
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppScreen(context: Context) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ServicesUI(context = context)

        //Fun1()
        //Fun2()
        //Fun3()
        //Fun4()
        //Fun5()
        //Fun6()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ServicesUI(context: Context) {
    val serviceStatus = remember {
        mutableStateOf(false)
    }
    val buttonValue = remember {
        mutableStateOf("Start Service")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (serviceStatus.value) {
                serviceStatus.value = !serviceStatus.value
                buttonValue.value = "Start Service"
                context.stopService(Intent(context, MyService::class.java))
            } else {
                serviceStatus.value = !serviceStatus.value
                buttonValue.value = "Stop Service"
                context.startForegroundService(Intent(context, MyService::class.java))
            }
        }) {
            Text(
                text = buttonValue.value,
                modifier = Modifier.padding(10.dp),
                fontSize = 20.sp
            )
        }
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
                channel.send(user)
            }
            channel.close()
        }
        for (user in channel) {
            textList.add(user)
        }
    }
    LazyRow(content = {
        items(textList) {
            Text(text = " $it")
        }
    })
}