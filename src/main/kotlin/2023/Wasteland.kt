package `2023`

import kotlin.math.abs

class Wasteland {

    private var nodes: Map<String, Pair<String, String>> = mapOf()
    private var moveOrder: String = ""

    private fun parseInput(resourceFile: String) {
        val input = this::class.java.getResourceAsStream(resourceFile)?.bufferedReader()?.readLines()!!
        moveOrder = input[0]
        nodes = input.subList(2, input.size).toList().associate {
            val key = it.substringBefore("=").trim()
            val next = it.substringAfter("=").trim()
            val nextNodes = next.substring(1, next.lastIndex).split(",").map { e -> e.trim() }
            key to Pair(nextNodes.first(), nextNodes.last())
        }
    }

    private fun move(instruction: Char, currentPosition: String): String {
        return if (instruction == 'L') {
            nodes[currentPosition]!!.first
        } else {
            nodes[currentPosition]!!.second
        }
    }

    private fun moveMap(directions: String, startPosition: String, endPosition: String): Long {
        var moveCounter = 0L
        val directionsLength = directions.length
        var currentPosition = startPosition
        while (currentPosition != endPosition) {
            currentPosition = move(directions[(moveCounter % directionsLength).toInt()], currentPosition)
            moveCounter++
        }
        return moveCounter
    }

    fun gcd(a: Long, b: Long): Long {
        var num1 = a
        var num2 = b
        while (num2 != 0L) {
            val temp = num2
            num2 = num1 % num2
            num1 = temp
        }
        return num1
    }

    fun calculateLCM(a: Long, b: Long): Long {
        // gcd
        return abs((a / gcd(a, b)) * b)
    }

    fun calculateLCMofList(numbers: List<Long>): Long {
        var result = numbers[0]
        for (i in 1 until numbers.size) {
            result = calculateLCM(result, numbers[i])
        }
        return result
    }
    fun executePart1(resourceFile: String): Long {
        parseInput(resourceFile)
        return moveMap(moveOrder, "AAA", "ZZZ")
    }

    fun executePart2(resourceFile: String): Long {
        parseInput(resourceFile)
        val startpositions = nodes.keys.filter { it.endsWith('A') }.toList()
        val counters = mutableListOf<Long>()
        for (startPosition in startpositions) {
            var pos = startPosition
            var count = 0L
            while (!pos.endsWith('Z')) {
                pos = move(moveOrder[(count % moveOrder.length.toLong()).toInt()], pos)
                count++
            }
            counters.add(count)
        }
        return calculateLCMofList(counters)
    }
}