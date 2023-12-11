package `2023`

import kotlin.math.abs

/**
 * 2023 - Day 11
 * Part 1: find the shortest path between all vertices, empty rows and cols have weight 2
 * Part 2: find the shortest path between all vertices, empty rows and cols have weight 1Mio
 */
class CosmicExpansion {

    fun transpose(input: List<String>): List<String> {
        val transposed = mutableListOf<String>()
        for ((i, line) in input.withIndex()) {
            for ((j, char) in line.withIndex()) {
                if (i == 0) {
                    transposed.add("$char")
                } else {
                    transposed[j] = transposed[j] + char
                }
            }
        }
        return transposed
    }

    fun findEmptyRowsAndCols(input: List<String>): List<List<Long>> {
        val listRows = mutableListOf<Long>()
        val listColumns = mutableListOf<Long>()
        // horizontal
        for ((index, line) in input.withIndex()) {
            if (!line.contains('#')) {
                // line is empty -> double
                listRows.add(index.toLong())
            }
        }
        val transposedInput = transpose(input)
        // vertical
        for ((index, line) in transposedInput.withIndex()) {
            if (!line.contains('#')) {
                // line is empty -> double
                listColumns.add(index.toLong())
            }
        }
        return listOf(listRows, listColumns)
    }

    fun buildGraph(input: List<String>, emptyRows: List<Long>, emptyCols: List<Long>): List<List<Pair<Long, Long>>> {
        var distColP1 = -1L
        var distColP2 = -1L
        val matrixP1 = mutableListOf<Pair<Long, Long>>()
        val matrixP2 = mutableListOf<Pair<Long, Long>>()
        for ((k, row) in input.withIndex()) {
            if (k.toLong() in emptyRows) {
                distColP1 += 2
                distColP2 += 1_000_000
            } else {
                distColP1 += 1
                distColP2 += 1
            }
            var distRowP1 = -1L
            var distRowP2 = -1L
            for ((l, col) in row.withIndex()) {
                if (l.toLong() in emptyCols) {
                    distRowP1 += 2
                    distRowP2 += 1_000_000
                } else {
                    distRowP1 += 1
                    distRowP2 += 1
                }
                if (col == '#') {
                    matrixP1.add(Pair(distRowP1, distColP1))
                    matrixP2.add(Pair(distRowP2, distColP2))
                }
            }
        }
        return listOf(matrixP1, matrixP2)
    }

    fun calculateDistance(matrix: List<Pair<Long, Long>>): Long {
        var distance = 0L
        for ((i, startNode)  in matrix.withIndex()) {
            for (destNode in matrix.subList(i + 1, matrix.size)) {
                distance += abs(startNode.first - destNode.first) + abs(startNode.second - destNode.second)
            }
        }
        return distance
    }

    fun execute(resourceFile: String): Pair<Long, Long> {
        val lines = this::class.java.getResourceAsStream(resourceFile)?.bufferedReader()?.readLines()!!
        val emptyRowsAndCols = findEmptyRowsAndCols(lines)
        val graph = buildGraph(lines, emptyRowsAndCols.first(), emptyRowsAndCols.last())
        return Pair(calculateDistance(graph.first()), calculateDistance(graph.last()))
    }
}