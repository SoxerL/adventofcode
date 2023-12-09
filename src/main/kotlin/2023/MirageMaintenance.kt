package `2023`

/**
 * 2023 - day 9
 * Each line is a sequence
 * Part 1: get sum of all next elements in sequence
 * Part 2: get sum of all previous elements in sequence
 */
class MirageMaintenance {

    /**
     * returns Pair(previousElement, nextElement) of a sequence using recursion
     */
    fun extraPolateSequence(sequence: List<Int>): Pair<Int, Int> {
        val nextSequence = sequence.zipWithNext{ a, b -> b - a }
        return if (nextSequence.toSet().size == 1) {
            Pair(sequence.first() - nextSequence.first() , sequence.last() + nextSequence.last())
        } else {
            val next = extraPolateSequence(nextSequence)
            Pair(sequence.first() - next.first, sequence.last() + next.second)
        }
    }

    fun execute(resourceFile: String): Pair<Int, Int> {
        val input = this::class.java.getResourceAsStream(resourceFile)?.bufferedReader()?.readLines()!!
            .map { it.split(" ").map(String::toInt) }.map { extraPolateSequence(it) }
            .reduce { acc, item -> Pair(acc.first + item.first, acc.second + item.second) }
        return input
    }

    // alternative solution, generates the sequences twice
    fun List<Int>.next(): Int {
        return lastOrNull()?.let { it + zipWithNext { a, b -> b - a }.next() } ?: 0
    }

    fun executeAlt(resourceFile: String): Pair<Int, Int> {
        val input = this::class.java.getResourceAsStream(resourceFile)?.bufferedReader()?.readLines()!!
            .map { it.split(" ").map(String::toInt) }
        return Pair(input.sumOf { it.next() }, input.sumOf { it.asReversed().next() })
    }

}