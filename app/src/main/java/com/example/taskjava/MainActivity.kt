package com.example.taskjava

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
import com.example.taskjava.ui.theme.TaskJavaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskJavaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        text = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        println("-------------------task1---------------------")
        task1()

        println("-------------------task2---------------------")
        task2()

        println("-------------------task3---------------------")
        task3()
    }
}

fun task1() {
    val arr = intArrayOf(10, 7, 8, 9, 1, 5)
    println("Исходный массив: ${arr.joinToString(", ")}")

    QuickSort.quickSort(arr, 0, arr.size - 1)

    println("Отсортированный массив: ${arr.joinToString(", ")}")
}

fun task2() {
    val worker = WorkerThread()
    worker.start()

    worker.execute {
        println("Задача 1 выполняется")
        Thread.sleep(1000)
        println("Задача 1 завершена")
    }

    worker.execute {
        println("Задача 2 выполняется")
        Thread.sleep(500)
        println("Задача 2 завершена")
    }

    Thread.sleep(2000)

    worker.shutdown()
}

fun task3() {
    val text = "hello world"

    val root = Huffman.buildHuffmanTree(text)

    val codeMap = mutableMapOf<Char, String>()
    Huffman.generateCodes(root, "", codeMap)

    val encodedText = Huffman.encodeText(text, codeMap)
    println("Закодированный текст: $encodedText")

    val decodedText = Huffman.decodeText(encodedText, root)
    println("Декодированный текст: $decodedText")
}