package `2023`

class Trebuchet {

    /**
     * Converts a string into a two-digit number by concatenating the first and last digit found in  the string
     */
    fun convertStringToNumber(input: String): Int {
        var firstDigit = -1
        var secondDigit = -1

        // check for spelled numbers
        val spelledNumbers = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)
        var index = 0
        while (index < input.length) {
            val currentChar = input[index]
            if (currentChar.isDigit()) {
                val currentDigit = currentChar.digitToInt()
                if (firstDigit == -1) {
                    firstDigit = currentDigit
                }
                secondDigit = currentDigit
            } else {
                val remainingString = input.substring(index)
                var foundValue = -1
                for (num in spelledNumbers.keys) {
                    if (remainingString.startsWith(num)) {
                        foundValue = spelledNumbers[num]!!
                    }
                }
                if (foundValue > 0) {
                    if (firstDigit == -1) firstDigit = foundValue
                    secondDigit = foundValue
                }
            }
            index++
        }
        return "$firstDigit$secondDigit".toInt()
    }

    fun execute(resourceFile: String): Int? {
        return this::class.java.getResourceAsStream(resourceFile)?.bufferedReader()?.readLines()
            ?.sumOf { convertStringToNumber(it) }
    }

}