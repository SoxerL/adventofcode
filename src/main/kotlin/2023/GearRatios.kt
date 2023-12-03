package `2023`

/**
 * 2023 - Day 3
 * Part 1: What is the sum of numbers adjacent to a symbol?
 * Part 2: What is the sum of the multiplied numbers adjacent to a * for each star with exactly 2 adjacent numbers?
 */
class GearRatios {

    private fun getAdjacentNumbersForSymbol(input: String, symbolPosition: Int, lineLength: Int): List<Int> {
        // each symbol has exactly 8 adjacent fields
        val lineStart = symbolPosition / lineLength * lineLength
        val lineEnd = (symbolPosition / lineLength + 1) * lineLength
        val adjacentNumbers = mutableListOf<Int>()
        val lineMultiplier = symbolPosition / lineLength

        // upper
        if (lineStart - lineLength >= 0) {
            val upperLineBounds = findBounds(input.substring(lineStart - lineLength, lineEnd - lineLength), (symbolPosition - lineLength) % lineLength)
            adjacentNumbers.addAll(findNumbersInString(input.substring(upperLineBounds.first + ((lineMultiplier - 1) * lineLength), upperLineBounds.second + ((lineMultiplier - 1) * lineLength))))
        }
        // current
        val currentLineBounds = findBounds(input.substring(lineStart, lineEnd), symbolPosition % lineLength)
        adjacentNumbers.addAll(findNumbersInString(input.substring(currentLineBounds.first + (lineMultiplier * lineLength), currentLineBounds.second + (lineMultiplier * lineLength))))

        // lower
        if (lineEnd + lineLength <= input.length) {
            val lowerLineBounds = findBounds(input.substring(lineStart + lineLength, lineEnd + lineLength), symbolPosition % lineLength)
            adjacentNumbers.addAll(findNumbersInString(input.substring(lowerLineBounds.first + ((lineMultiplier + 1) * lineLength), lowerLineBounds.second + ((lineMultiplier + 1) * lineLength))))
        }
        return adjacentNumbers
    }

    fun findBounds(line: String, startPos: Int): Pair<Int, Int> {
        // left
        var leftPos = if (startPos - 1 < 0) 0 else startPos - 1
        while (line[leftPos].isDigit() && leftPos != 0) {
            // try to move left
            leftPos--
        }

        // right
        val rightBoundary = line.length
        var rightPos = if (startPos + 1 >= rightBoundary) rightBoundary - 1 else startPos + 1
        while (rightPos != rightBoundary && line[rightPos].isDigit()) {
            // try to move right
            rightPos++
        }
        return Pair(leftPos, rightPos)
    }

    private fun findNumbersInString(line: String): List<Int> {
        return Regex("[0-9]+").findAll(line).map { it.value.toInt() }.toList()
    }

    fun execute(resourceFile: String): Pair<Int, Int> {
        // Sum of all numbers that are adjacent to a symbol (anything not .)
        val lines = this::class.java.getResourceAsStream(resourceFile)?.bufferedReader()?.readLines()!!
        val lineLength = lines[0].length
        val flatInput = lines.joinToString("")
        val symbolRegex = Regex("[^0-9.]")
        val partNumbers = mutableListOf<Int>()
        val gearRatios = mutableListOf<Int>()

        for ((index, c) in flatInput.withIndex()) {
            if (symbolRegex.matches(c.toString())) {
                val currentNumbers = getAdjacentNumbersForSymbol(flatInput, index, lineLength)
                partNumbers.addAll(currentNumbers)
                if (currentNumbers.size == 2) {
                    gearRatios.add(currentNumbers.first() * currentNumbers.last())
                }
            }
        }
        return Pair(partNumbers.sum(), gearRatios.sum())
    }
}