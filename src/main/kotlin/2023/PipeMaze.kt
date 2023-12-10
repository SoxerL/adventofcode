package `2023`

import java.io.File

/**
 * 2023 - Day 10
 * Each line shows
 * Part 1:
 * Part 2:
 */
class PipeMaze {

    private lateinit var changedMaze: String

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
                        currentPos -= lineLength
                        currentMove = input[currentPos]
                        currentDirection = Direction.UP
                    } else if (currentDirection == Direction.DOWN && currentPos < input.length - lineLength) {
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
                        currentMove = input[++currentPos]
                        currentDirection = Direction.RIGHT
                    } else if (currentDirection == Direction.LEFT && currentPos % lineLength > 0) {
                        currentMove = input[--currentPos]
                        currentDirection = Direction.LEFT
                    } else {
                        // nextMove not possible -> no Cycle
                        return -1
                    }
                }

                'L' -> {
                    if (currentDirection == Direction.DOWN && currentPos % lineLength < lineLength - 1) {
                        currentMove = input[++currentPos]
                        currentDirection = Direction.RIGHT
                    } else if (currentDirection == Direction.LEFT && currentPos > lineLength) {
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
                        currentMove = input[--currentPos]
                        currentDirection = Direction.LEFT
                    } else if (currentDirection == Direction.RIGHT && currentPos > lineLength) {
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
                        currentMove = input[--currentPos]
                        currentDirection = Direction.LEFT
                    } else if (currentDirection == Direction.RIGHT && currentPos < input.length - lineLength) {
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
                        currentMove = input[++currentPos]
                        currentDirection = Direction.RIGHT
                    } else if (currentDirection == Direction.LEFT && currentPos < input.length - lineLength) {
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
        return cycleLength
    }

    fun getCycleLength(startPos: Int, input: String, lineLength: Int): Int {
        val cycles = mutableListOf<Int>()
        // check right
        val rightMove = input[startPos + 1]
        if (rightMove in "-J7") {
            cycles.add(calculateCycleLengthIterative(startPos + 1, Direction.RIGHT, input, lineLength))
        }
        // check down
        val downMove = input[startPos + lineLength]
        if (downMove in "|LJ") {
            cycles.add(calculateCycleLengthIterative(startPos + lineLength, Direction.DOWN, input, lineLength))
        }
        // check left
        val leftMove = input[startPos - 1]
        if (leftMove in "-LF") {
            cycles.add(calculateCycleLengthIterative(startPos - 1, Direction.LEFT, input, lineLength))
        }
        // check up
        val upMove = input[startPos - lineLength]
        if (upMove in "|7F") {
            cycles.add(calculateCycleLengthIterative(startPos - lineLength, Direction.UP, input, lineLength))
        }
        return cycles.max() / 2
    }

    fun markOuterDots(lineLength: Int) {
        var currentIndex = 0
        val tmpMaze = changedMaze.toCharArray()
        // top down
        while (currentIndex < changedMaze.length) {
            if (changedMaze[currentIndex] == '.') {
                if (checkForAdjacentOuter(currentIndex, lineLength)) {
                     tmpMaze[currentIndex] = 'O'
                    changedMaze = tmpMaze.concatToString()
                }
            }
            currentIndex++
        }
        // bottom up

    }

    fun writeOutput(lineLength: Int) {
        var counter = 0
        val f = File("day10-output")
        while (counter < changedMaze.length - lineLength) {
            val line = changedMaze.substring(counter, counter + lineLength)
            counter += lineLength
            f.appendText(line)
            f.appendText("\n")
        }
    }

    /*


    fun calculateAreaOfCycle(lineLength: Int): Int {
        var area = 0
        var counter = 0
        val f = File("day10-output")
        while (counter < changedMaze.size - lineLength) {
            val line = changedMaze.concatToString().substring(counter, counter + lineLength)
            counter += lineLength

            f.appendText(line)
            f.appendText("\n")
            area += lineEnclosedArea(line)
        }
        return area
    }

    fun lineEnclosedArea(line: String): Int {
        val xCount = line.count { it == 'X' }
        var area = 0
        if (xCount == 0) {
            return 0
        }
        if (xCount %2 == 0) {
            var isSecond = false
            var previousIndex = 0
            for ((index, c) in line.withIndex()) {
                if (c == 'X') {
                    if (isSecond) {
                        area += index - previousIndex - 1
                        isSecond = false
                    } else {
                        isSecond = true
                        previousIndex = index
                    }
                }
            }
        } else {
            lineEnclosedArea(line.substring(line.indexOfFirst { it == 'X' } + 1))
        }
        return area
    }

     */

    /**
     * if an adjacent field is not part of cycle area the current field cannot be part of it either
     * returns true if is outer, returns false if no adjacentField is 0 -> could still be outer but needs different checks
     */
    private fun checkForAdjacentOuter(symbolPosition: Int, lineLength: Int): Boolean {
        // each symbol has exactly 8 adjacent fields
        val lineStart = symbolPosition / lineLength * lineLength
        val lineEnd = (symbolPosition / lineLength + 1) * lineLength
        // current
        if (symbolPosition == 0 || (symbolPosition - 1) % lineLength == lineLength - 1 || changedMaze[symbolPosition - 1] == 'O') {
            // left bound
            return true
        } else if ((symbolPosition + 1) % lineLength == 0 || changedMaze[symbolPosition + 1] == 'O') {
            // right bound
            return true
        }
        // upper
        if (lineStart - lineLength >= 0) {
            // check if adjacent to O
            if ((symbolPosition - 1 - lineLength) % lineLength == lineLength - 1 || changedMaze[symbolPosition - 1 - lineLength] == 'O') {
                // left bound
                return true
            } else if ((symbolPosition + 1 - lineLength) % lineLength == 0 || changedMaze[symbolPosition + 1 - lineLength] == 'O') {
                // right bound
                return true
            }
        } else {
            // at the edge, cannot be part
            return true
        }
        // lower
        if (lineEnd + lineLength <= changedMaze.length) {
            // check if adjacent to O
            if ((symbolPosition - 1 + lineLength) % lineLength == lineLength - 1 || changedMaze[symbolPosition - 1 + lineLength] == 'O') {
                // left bound
                return true
            } else if ((symbolPosition + 1 + lineLength) % lineLength == 0 || changedMaze[symbolPosition + 1 + lineLength] == 'O') {
                // right bound
                return true
            }
        } else {
            // at edge cannot be part
            return true
        }
        return false
    }

    fun execute(resourceFile: String): Pair<Int, Int> {
        val input = this::class.java.getResourceAsStream(resourceFile)?.bufferedReader()?.readLines()!!
        val lineLength = input[0].length
        val continuousInput = input.joinToString("")
        changedMaze = continuousInput
        val statPosition = continuousInput.indexOf('S')
        markOuterDots(lineLength)
        writeOutput(lineLength)
        return Pair(getCycleLength(statPosition, continuousInput, lineLength), changedMaze.count { it == '.' })
    }
}

enum class Direction {
    RIGHT,
    LEFT,
    UP,
    DOWN
}