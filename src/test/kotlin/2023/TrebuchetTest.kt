package `2023`

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TrebuchetTest {

    private lateinit var trebuchet: Trebuchet

    @BeforeEach
    fun setup() {
        trebuchet = Trebuchet()
    }

    @Test
    fun `convert digit string to number`() {
        val res = trebuchet.convertStringToNumber("12")
        assertEquals(12, res)
    }

    @Test
    fun `convert alphanumeric string starting and ending with a digit to number`() {
        val res = trebuchet.convertStringToNumber("3he-\$llo4")
        assertEquals(34, res)
    }

    @Test
    fun `convert alphanumeric string with two-digits in the middle to number`() {
        val res = trebuchet.convertStringToNumber("h.e5l\\l6o")
        assertEquals(56, res)
    }

    @Test
    fun `convert alphanumeric string with only one digit to number`() {
        val res = trebuchet.convertStringToNumber("h-.ui,7!=cv)(/&%asdfç*+")
        assertEquals(77, res)
    }

    @Test
    fun `convert alphanumeric string with more than two digits to number`() {
        val res = trebuchet.convertStringToNumber("8hane,%1l3iu4HBVC56POL9?__hjkh")
        assertEquals(89, res)
    }

    @Test
    fun `calculate sum of numbers from lines in part 1 test file`() {
        val res = trebuchet.execute("day1-part1-test-input")
        assertEquals(142, res)
    }

    @Test
    fun `convert spelled out numbers to integers`() {
        val spelledNumbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        var expectedResult = 11
        for (s in spelledNumbers) {
            val res = trebuchet.convertStringToNumber(s)
            assertEquals(expectedResult, res)
            expectedResult += 11
        }
    }

    @Test
    fun `calculate sum of numbers from lines in part 2 test file`() {
        val res = trebuchet.execute("day1-part2-test-input")
        assertEquals(281, res)
    }

}