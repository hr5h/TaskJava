package com.example.taskjava

object QuickSort {
    fun quickSort(arr: IntArray, low: Int, high: Int) {
        if (low < high) {
            val pi = partition(arr, low, high)
            quickSort(arr, low, pi - 1)
            quickSort(arr, pi + 1, high)
        }
    }

    private fun partition(arr: IntArray, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1

        for (j in low until high) {
            if (arr[j] <= pivot) {
                i++
                arr[i] = arr[j].also { arr[j] = arr[i] }
            }
        }

        arr[i + 1] = arr[high].also { arr[high] = arr[i + 1] }
        return i + 1
    }
}