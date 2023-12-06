package `2023`

import kotlin.math.ceil
import kotlin.math.sqrt

class WaitForIt {

    fun timeRangeToBeatDistance(timeMax: Double, distance: Long): Long {
        val upperBound = ceil((timeMax + sqrt(timeMax * timeMax - 4 * distance)) / 2 - 1)
        val lowerBound = ((timeMax - sqrt(timeMax * timeMax - 4 * distance)) / 2).toLong() + 1
        return upperBound.toLong() - lowerBound + 1
    }

    fun execute(resourceFile: String): Pair<Long, Long> {
        val lines = this::class.java.getResourceAsStream(resourceFile)?.bufferedReader()?.readLines()!!
        // part 1
        val timesMax = lines[0].substringAfter(":").trim().split("\\s+".toRegex()).map { it.toDouble() }
        val distancesToBeat = lines[1].substringAfter(":").trim().split("\\s+".toRegex()).map { it.toLong() }
        var numberOfWaysToWin = 1L
        for (i in timesMax.indices) {
            numberOfWaysToWin *= timeRangeToBeatDistance(timesMax[i], distancesToBeat[i])
        }

        // part2
        val timeMax = lines[0].substringAfter(":").filterNot { it.isWhitespace() }.toDouble()
        val distance = lines[1].substringAfter(":").filterNot { it.isWhitespace() }.toLong()
        return Pair(numberOfWaysToWin, timeRangeToBeatDistance(timeMax, distance))
    }
}