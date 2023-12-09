package `2023`

import java.lang.IllegalArgumentException
import kotlin.math.pow

/**
 * 2023 - day 7
 * Given a set of pokerhands and winnings rank each hand according to its type and card by card from left to right
 * Part 1: sum winnings by inversePlace * winnings
 * Part 2: sum winnings by inversePlace * winnings if J is a joker and J is the least valued card individually
 */
class CamelCards {

    fun determinType(cards: String): HandType {
        val occurences = cards.groupingBy { it }.eachCount()
        return when (occurences.values.max()) {
            1 -> HandType.HIGHCARD
            4 -> HandType.FOURKIND
            5 -> HandType.FIVEKIND
            else -> {
                var countPairs = 0
                var countThrees = 0
                for (occurence in occurences.values) {
                    if (occurence == 2) countPairs++
                    if (occurence == 3) countThrees++
                }
                if (countPairs == 1) {
                    if (countThrees == 0) HandType.PAIR
                    else HandType.FULLHOUSE
                } else if (countPairs == 2) {
                    HandType.TWOPAIR
                } else {
                    HandType.THREEKIND
                }
            }
        }
    }

    fun determinTypeWithJokers(cards: String): HandType {
        val occurences = cards.groupingBy { it }.eachCount()
        val jokerCount = occurences.getOrDefault('J', 0)
        if (jokerCount == 0) {
            return determinType(cards)
        }
        if (jokerCount == 5) {
            return HandType.FIVEKIND
        }
        val occurencesWithoutJoker = occurences.filter { it.key != 'J' }.toMap()
        val maxValue = occurencesWithoutJoker.values.max()
        when (jokerCount) {
            4 -> return HandType.FIVEKIND
            3 -> {
                return if (maxValue == 2) HandType.FIVEKIND
                        else HandType.FOURKIND
            }
            2 -> {
                return when (maxValue) {
                    1 -> HandType.THREEKIND
                    2 -> HandType.FOURKIND
                    3 -> HandType.FIVEKIND
                    else -> throw IllegalArgumentException("maxValue must be between 1<=count<=3")
                }
            }
            1 -> {
                when (maxValue) {
                    1 -> return HandType.PAIR
                    2 -> {
                        return if (occurencesWithoutJoker.values.count { it == 2 } > 1) {
                            HandType.FULLHOUSE
                        } else {
                            HandType.THREEKIND
                        }
                    }
                    3 -> {
                        return HandType.FOURKIND
                    }
                    4 -> {
                        return HandType.FIVEKIND
                    }
                    else -> {
                        throw IllegalArgumentException("maxValue count must be 1 <= count <= 4")
                    }
                }
            }
            else -> {
                throw IllegalArgumentException("Joker count must be 0 <= count <= 5")
            }
        }
    }

    fun splitByType(items: List<Pair<String, Long>>): List<List<Pair<String, Long>>> {
        return items.groupBy { determinType(it.first) }.values.toList()
    }

    fun splitByType2(items: List<Pair<String, Long>>): List<List<Pair<String, Long>>> {
        return items.groupBy { determinTypeWithJokers(it.first) }.values.toList()
    }

    fun rankHand(hand: Pair<String, Long>): Int {
        // A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, 2
        val ranks = "23456789TJQKA"
        var sum = 0
        val base = 20.0
        for ((index, c) in hand.first.withIndex()) {
            sum += (ranks.indexOf(c) + 1) * base.pow(4- index).toInt()
        }
        return sum
    }

    fun rankHand2(hand: Pair<String, Long>): Int {
        // A, K, Q, T, 9, 8, 7, 6, 5, 4, 3, 2, J
        val ranks = "J23456789TQKA"
        var sum = 0
        val base = 20.0
        for ((index, c) in hand.first.withIndex()) {
            sum += (ranks.indexOf(c) + 1) * base.pow(4- index).toInt()
        }
        return sum
    }


    fun execute(resourceFile: String): Pair<Long, Long> {
        val input = this::class.java.getResourceAsStream(resourceFile)?.bufferedReader()?.readLines()!!
            .map { val tmp = it.trim().split(" "); Pair(tmp[0], tmp[1].toLong()) }
        val inputSplitAndSortedByTypePart1 = splitByType(input).map { e -> e.sortedByDescending { rankHand(it) } }
        val inputSplitAndSortedByTypePart2 = splitByType2(input).map { e -> e.sortedByDescending { rankHand2(it) } }
        // sort groupings
        // Part1
        val inputSortedPart1: Array<List<Pair<String, Long>>?> = arrayOfNulls(7)
        for (group in inputSplitAndSortedByTypePart1) {
            when (determinType(group.first().first)) {
                HandType.HIGHCARD -> inputSortedPart1[6] = group
                HandType.PAIR -> inputSortedPart1[5] = group
                HandType.TWOPAIR -> inputSortedPart1[4] = group
                HandType.THREEKIND -> inputSortedPart1[3] = group
                HandType.FULLHOUSE -> inputSortedPart1[2] = group
                HandType.FOURKIND -> inputSortedPart1[1] = group
                HandType.FIVEKIND -> inputSortedPart1[0] = group
            }
        }
        // Part2
        val inputSortedPart2: Array<List<Pair<String, Long>>?> = arrayOfNulls(7)
        for (group in inputSplitAndSortedByTypePart2) {
            when (determinTypeWithJokers(group.first().first)) {
                HandType.HIGHCARD -> inputSortedPart2[6] = group
                HandType.PAIR -> inputSortedPart2[5] = group
                HandType.TWOPAIR -> inputSortedPart2[4] = group
                HandType.THREEKIND -> inputSortedPart2[3] = group
                HandType.FULLHOUSE -> inputSortedPart2[2] = group
                HandType.FOURKIND -> inputSortedPart2[1] = group
                HandType.FIVEKIND -> inputSortedPart2[0] = group
            }
        }
        // sum winnings
        var totalWinningsPart1 = 0L
        var totalWinningsPart2 = 0L
        var multiplierPart1 = input.size
        var multiplierPart2 = input.size
        for (i in 0..6) {
            if (inputSortedPart1[i] != null) {
                for (hand in inputSortedPart1[i]!!) {
                    totalWinningsPart1 += multiplierPart1 * hand.second
                    multiplierPart1--
                }
            }
            if (inputSortedPart2[i] != null) {
                for (hand in inputSortedPart2[i]!!) {
                    totalWinningsPart2 += multiplierPart2 * hand.second
                    multiplierPart2--
                }
            }
        }
        return Pair(totalWinningsPart1, totalWinningsPart2)
    }
}

enum class HandType{
    HIGHCARD,
    PAIR,
    TWOPAIR,
    THREEKIND,
    FULLHOUSE,
    FOURKIND,
    FIVEKIND
}