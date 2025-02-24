package com.example.taskjava

import java.util.PriorityQueue

data class HuffmanNode(
    val char: Char? = null,
    val frequency: Int,
    val left: HuffmanNode? = null,
    val right: HuffmanNode? = null
) : Comparable<HuffmanNode> {

    override fun compareTo(other: HuffmanNode): Int {
        return this.frequency - other.frequency
    }
}

object Huffman {

    fun buildHuffmanTree(text: String): HuffmanNode {
        val frequencyMap = mutableMapOf<Char, Int>()
        for (char in text) {
            frequencyMap[char] = frequencyMap.getOrDefault(char, 0) + 1
        }

        val priorityQueue = PriorityQueue<HuffmanNode>()
        for ((char, frequency) in frequencyMap) {
            priorityQueue.add(HuffmanNode(char, frequency))
        }

        while (priorityQueue.size > 1) {
            val left = priorityQueue.poll()
            val right = priorityQueue.poll()
            val parent = HuffmanNode(
                frequency = left.frequency + right.frequency,
                left = left,
                right = right
            )
            priorityQueue.add(parent)
        }

        return priorityQueue.poll()
    }

    fun generateCodes(root: HuffmanNode, code: String = "", codeMap: MutableMap<Char, String>) {
        if (root.char != null) {
            codeMap[root.char] = code
            return
        }
        generateCodes(root.left!!, code + "0", codeMap)
        generateCodes(root.right!!, code + "1", codeMap)
    }

    fun encodeText(text: String, codeMap: Map<Char, String>): String {
        return text.map { codeMap[it] }.joinToString("")
    }

    fun decodeText(encodedText: String, root: HuffmanNode): String {
        val decodedText = StringBuilder()
        var currentNode = root

        for (bit in encodedText) {
            currentNode = if (bit == '0') currentNode.left!! else currentNode.right!!
            if (currentNode.char != null) {
                decodedText.append(currentNode.char)
                currentNode = root
            }
        }

        return decodedText.toString()
    }
}