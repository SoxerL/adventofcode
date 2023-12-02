package `2023`

/**
 * 2023 - Day 2
 */
class CubeConundrum {

    fun parseGameNumber(input: String): Int {
        return input.substringAfter("Game ").substringBefore(": ").toInt()
    }

    fun parseMaxNumberPerColor(input: String): Triple<Int, Int, Int> {
        val tokens = input.substringAfter(":").replace(Regex("[^A-Za-z0-9 ]"), "").split(" ")
        var redMax = 0
        var greenMax = 0
        var blueMax = 0

        for ((index, token) in tokens.withIndex()) {
            if (token.contains("red")) {
                val tmpRed = tokens[index - 1].toInt()
                if (tmpRed > redMax) redMax = tmpRed
            } else if (token.contains("green")) {
                val tmpGreen = tokens[index - 1].toInt()
                if (tmpGreen > greenMax) greenMax = tmpGreen
            } else if (token.contains("blue")) {
                val tmpBlue = tokens[index - 1].toInt()
                if (tmpBlue > blueMax) blueMax = tmpBlue
            }
        }
        return Triple(redMax, greenMax, blueMax)
    }

    fun executePart1(resourceFile: String, redMax: Int, greenMax: Int, blueMax: Int): Pair<Int, Int> {
        var sum = 0
        var power = 0
        this::class.java.getResourceAsStream(resourceFile)?.bufferedReader()?.readLines()?.forEach {
            val actualValues = parseMaxNumberPerColor(it)
            if (actualValues.first <= redMax && actualValues.second <= greenMax && actualValues.third <= blueMax) {
                sum += parseGameNumber(it)
            }
            power += actualValues.first * actualValues.second * actualValues.third
        }
        return Pair(sum, power)
    }
}