package `2023`

/**
 * 2023 - Day4
 * Part 1: How many Points are there for all Games? Each number on both sides of | doubles the points per Game starting at 1
 * PArt 2: How many Cards are there? Each number on both sides of | adds a card of for the x next games where x is # of numbers on both sides
 */
class Scratchcards {

    fun parseInput(input: String): Pair<Set<Int>, Set<Int>> {
        val winningNumbers = input.substringBefore("|").trim().split("\\s+".toRegex()).map { it.toInt() }.toSet()
        val numbers = input.substringAfter("|").trim().split("\\s+".toRegex()).map { it.toInt() }.toSet()
        return Pair(winningNumbers, numbers)
    }

    fun execute(resourceFile: String): Pair<Int, Int> {
        val lines = this::class.java.getResourceAsStream(resourceFile)?.bufferedReader()?.readLines()!!
            .map { it.substringAfter(": ") }
        var sumPoints = 0
        val mapNumCards = mutableMapOf<Int, Int>()
        // initialize tracking map for part 2
        for (i in 1..lines.size) {
            mapNumCards[i] = 1
        }
        for ((index, line) in lines.withIndex()) {
            val numberLists = parseInput(line)
            val res = numberLists.first.intersect(numberLists.second)

            if (res.isNotEmpty()) {
                // part 1
                sumPoints += 1 shl (res.size - 1)

                // part 2
                for (newCards in 1..res.size) {
                    mapNumCards[index + newCards + 1] = mapNumCards[index + newCards + 1]!! + mapNumCards[index + 1]!!
                }
            }
        }
        return Pair(sumPoints, mapNumCards.values.sum())
    }
}