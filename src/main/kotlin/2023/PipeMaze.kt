package `2023`

import java.lang.IllegalArgumentException

/**
 * 2023 - Day 10
 * Each line shows
 * Part 1:
 * Part 2:
 */
class PipeMaze {
    private val grid: MutableList<Node> = mutableListOf()
    private var startPos = -1
    private var lineLength = 0

    private fun buildGrid(input: String) {
        for ((index, c) in input.withIndex()) {
            grid.add(Node(index, c, Direction.UNKNOWN))
        }
    }

    fun calculateCycleLength(pos: Int, direction: Direction, input: String, lineLength: Int): Int {
        // recursive returns stackoverflow
        val currentMove = input[pos]
        when (currentMove) {
            '|' -> {
                return if (direction == Direction.UP && pos > lineLength) {
                    1 + calculateCycleLength(pos - lineLength, Direction.UP, input, lineLength)
                } else if (direction == Direction.DOWN && pos < input.length - lineLength) {
                    1 + calculateCycleLength(pos + lineLength, Direction.DOWN, input, lineLength)
                } else {
                    // nextMove not possible -> no Cycle
                    -1
                }
            }

            '-' -> {
                return if (direction == Direction.RIGHT && pos % lineLength < lineLength - 1) {
                    1 + calculateCycleLength(pos + 1, Direction.RIGHT, input, lineLength)
                } else if (direction == Direction.LEFT && pos % lineLength > 0) {
                    1 + calculateCycleLength(pos - 1, Direction.LEFT, input, lineLength)
                } else {
                    // nextMove not possible -> no Cycle
                    -1
                }
            }

            'L' -> {
                return if (direction == Direction.DOWN && pos % lineLength < lineLength - 1) {
                    1 + calculateCycleLength(pos + 1, Direction.RIGHT, input, lineLength)
                } else if (direction == Direction.LEFT && pos > lineLength) {
                    1 + calculateCycleLength(pos - lineLength, Direction.UP, input, lineLength)
                } else {
                    // nextMove not possible -> no Cycle
                    -1
                }
            }

            'J' -> {
                return if (direction == Direction.DOWN && pos % lineLength > 0) {
                    1 + calculateCycleLength(pos - 1, Direction.LEFT, input, lineLength)
                } else if (direction == Direction.RIGHT && pos > lineLength) {
                    1 + calculateCycleLength(pos - lineLength, Direction.UP, input, lineLength)
                } else {
                    // nextMove not possible -> no Cycle
                    -1
                }
            }

            '7' -> {
                return if (direction == Direction.UP && pos % lineLength > 0) {
                    1 + calculateCycleLength(pos - 1, Direction.LEFT, input, lineLength)
                } else if (direction == Direction.RIGHT && pos < input.length - lineLength) {
                    1 + calculateCycleLength(pos + lineLength, Direction.DOWN, input, lineLength)
                } else {
                    // nextMove not possible -> no Cycle
                    -1
                }
            }

            'F' -> {
                return if (direction == Direction.UP && pos % lineLength < lineLength - 1) {
                    1 + calculateCycleLength(pos + 1, Direction.RIGHT, input, lineLength)
                } else if (direction == Direction.LEFT && pos < input.length - lineLength) {
                    1 + calculateCycleLength(pos + lineLength, Direction.DOWN, input, lineLength)
                } else {
                    // nextMove not possible -> no Cycle
                    -1
                }
            }

            'S' -> {
                return 1
            }

            else -> {
                // is ground cycle not possible
                return -1
            }
        }
    }

    fun calculateCycleLengthIterative(pos: Int, direction: Direction, input: String, lineLength: Int): Int {
        var currentMove = input[pos]
        var currentDirection = direction
        var cycleLength = 1
        var currentPos = pos
        while (currentMove != 'S') {
            when (currentMove) {
                '|' -> {
                    if (currentDirection == Direction.UP && currentPos > lineLength) {
                        grid[currentPos].direction = Direction.UP
                        currentPos -= lineLength
                        currentMove = input[currentPos]
                        currentDirection = Direction.UP
                    } else if (currentDirection == Direction.DOWN && currentPos < input.length - lineLength) {
                        grid[currentPos].direction = Direction.DOWN
                        currentPos += lineLength
                        currentMove = input[currentPos]
                        currentDirection = Direction.DOWN
                    } else {
                        // nextMove not possible -> no Cycle
                        return -1
                    }
                }

                '-' -> {
                    if (currentDirection == Direction.RIGHT && currentPos % lineLength < lineLength - 1) {
                        grid[currentPos].direction = Direction.RIGHT
                        currentMove = input[++currentPos]
                        currentDirection = Direction.RIGHT
                    } else if (currentDirection == Direction.LEFT && currentPos % lineLength > 0) {
                        grid[currentPos].direction = Direction.LEFT
                        currentMove = input[--currentPos]
                        currentDirection = Direction.LEFT
                    } else {
                        // nextMove not possible -> no Cycle
                        return -1
                    }
                }

                'L' -> {
                    if (currentDirection == Direction.DOWN && currentPos % lineLength < lineLength - 1) {
                        grid[currentPos].direction = Direction.DOWN
                        currentMove = input[++currentPos]
                        currentDirection = Direction.RIGHT
                    } else if (currentDirection == Direction.LEFT && currentPos > lineLength) {
                        grid[currentPos].direction = Direction.UP
                        currentPos -= lineLength
                        currentMove = input[currentPos]
                        currentDirection = Direction.UP
                    } else {
                        // nextMove not possible -> no Cycle
                        return -1
                    }
                }

                'J' -> {
                    if (currentDirection == Direction.DOWN && currentPos % lineLength > 0) {
                        grid[currentPos].direction = Direction.DOWN
                        currentMove = input[--currentPos]
                        currentDirection = Direction.LEFT
                    } else if (currentDirection == Direction.RIGHT && currentPos > lineLength) {
                        grid[currentPos].direction = Direction.UP
                        currentPos -= lineLength
                        currentMove = input[currentPos]
                        currentDirection = Direction.UP
                    } else {
                        // nextMove not possible -> no Cycle
                        return -1
                    }
                }

                '7' -> {
                    if (currentDirection == Direction.UP && currentPos % lineLength > 0) {
                        grid[currentPos].direction = Direction.UP
                        currentMove = input[--currentPos]
                        currentDirection = Direction.LEFT
                    } else if (currentDirection == Direction.RIGHT && currentPos < input.length - lineLength) {
                        grid[currentPos].direction = Direction.DOWN
                        currentPos += lineLength
                        currentMove = input[currentPos]
                        currentDirection = Direction.DOWN
                    } else {
                        // nextMove not possible -> no Cycle
                        return -1
                    }
                }

                'F' -> {
                    if (currentDirection == Direction.UP && currentPos % lineLength < lineLength - 1) {
                        grid[currentPos].direction = Direction.UP
                        currentMove = input[++currentPos]
                        currentDirection = Direction.RIGHT
                    } else if (currentDirection == Direction.LEFT && currentPos < input.length - lineLength) {
                        grid[currentPos].direction = Direction.DOWN
                        currentPos += lineLength
                        currentMove = input[currentPos]
                        currentDirection = Direction.DOWN
                    } else {
                        // nextMove not possible -> no Cycle
                        return -1
                    }
                }

                else -> {
                    // is ground cycle not possible
                    return -1
                }
            }
            cycleLength++
        }
        // setStartDirection
        setStartDirection(direction, currentDirection, startPos)
        return cycleLength
    }

    fun setStartDirection(dirFirstStep: Direction, dirLastStep: Direction, startPos: Int) {
        when (dirFirstStep) {
            Direction.RIGHT -> {
                when (dirLastStep) {
                    Direction.RIGHT -> {
                        grid[startPos].direction = Direction.RIGHT
                    }

                    Direction.UP -> {
                        grid[startPos].direction = Direction.UP
                    }

                    Direction.DOWN -> {
                        grid[startPos].direction = Direction.DOWN
                    }

                    else -> throw IllegalArgumentException("Direction must be known")
                }
            }

            Direction.LEFT -> {
                when (dirLastStep) {
                    Direction.LEFT -> {
                        grid[startPos].direction = Direction.LEFT
                    }

                    Direction.UP -> {
                        grid[startPos].direction = Direction.UP
                    }

                    Direction.DOWN -> {
                        grid[startPos].direction = Direction.DOWN
                    }

                    else -> throw IllegalArgumentException("Direction must be known")
                }
            }

            Direction.UP -> grid[startPos].direction = Direction.UP
            Direction.DOWN -> grid[startPos].direction = Direction.DOWN
            Direction.UNKNOWN -> throw IllegalArgumentException("Direction at start must be known")
        }
    }

    fun getCycleLength(startPos: Int, input: String, lineLength: Int): Int {
        // check right
        val rightMove = input[startPos + 1]
        if (rightMove in "-J7") {
            val foundLength = calculateCycleLengthIterative(startPos + 1, Direction.RIGHT, input, lineLength)
            if (foundLength != -1) {
                return foundLength / 2
            }
        }
        // check left
        val leftMove = input[startPos - 1]
        if (leftMove in "-LF") {
            val foundLength = calculateCycleLengthIterative(startPos - 1, Direction.LEFT, input, lineLength)
            if (foundLength != -1) {
                return foundLength / 2
            }
        }
        // check down
        val downMove = input[startPos + lineLength]
        if (downMove in "|LJ") {
            val foundLength = calculateCycleLengthIterative(startPos + lineLength, Direction.DOWN, input, lineLength)
            if (foundLength != -1) {
                return foundLength / 2
            }
        }
        // check up
        val upMove = input[startPos - lineLength]
        if (upMove in "|7F") {
            val foundLength = calculateCycleLengthIterative(startPos - lineLength, Direction.UP, input, lineLength)
            if (foundLength != -1) {
                return foundLength / 2
            }
        }
        return -1
    }

    fun countInside(): Int {
        var area = 0
        var counter = 0
        while (counter < grid.size - lineLength) {
            val line = grid.subList(counter, counter + lineLength)
            var crossingCount = 0
            for (node in line) {
                if (node.direction == Direction.UNKNOWN) {
                    if (crossingCount % 2 == 1) {
                        // inside
                        area++
                    }
                } else if (node.symbol in "|JLS") {
                    crossingCount++
                }
            }
            counter += lineLength
        }
        return area
    }

    fun execute(resourceFile: String): Pair<Int, Int> {
        val input = this::class.java.getResourceAsStream(resourceFile)?.bufferedReader()?.readLines()!!
        lineLength = input[0].length
        val continuousInput = input.joinToString("")
        buildGrid(continuousInput)
        startPos = continuousInput.indexOf('S')
        return Pair(getCycleLength(startPos, continuousInput, lineLength), countInside())
    }
}

enum class Direction {
    RIGHT,
    LEFT,
    UP,
    DOWN,
    UNKNOWN
}

data class Node(
    val pos: Int,
    val symbol: Char,
    var direction: Direction
)