package `2023`

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CubeConundrumTest {

    private lateinit var cubeConundrum: CubeConundrum

    @BeforeEach
    fun setup() {
        cubeConundrum = CubeConundrum()
    }

    @Test
    fun `parseGameNumber returns one-digit number in String`() {
        val res = cubeConundrum.parseGameNumber("Game 1: whatever")
        assertEquals(1, res)
    }

    @Test
    fun `parseGameNumber can return multi-digit numbers from String`() {
        val res = cubeConundrum.parseGameNumber("Game 1234: whatever")
        assertEquals(1234, res)
    }

    @Test
    fun `parseGameNumber can return multi-digit numbers from alphanumeric String`() {
        val res = cubeConundrum.parseGameNumber("some-Game 78: polmbh 12 blue,$ red")
        assertEquals(78, res)
    }

    @Test
    fun `parseMaxNumberPerColor returns a Triple`() {
        val res = cubeConundrum.parseMaxNumberPerColor("Game 1: somesdtring, 0 blue, 0 red, 0 green")
        assertEquals(Triple(0,0,0), res)
    }

    @Test
    fun `parseMaxNumbersPerColor returns a Triple even if only two numbers are available`() {
        val res = cubeConundrum.parseMaxNumberPerColor("Game 1: 2 red,  1 blue, something else five two")
        assertEquals(Triple(2, 0, 1), res)
    }

    @Test
    fun `parseMaxNumbersPerColor returns maximum Numbers for each Color if they appear more than once`() {
        val res = cubeConundrum.parseMaxNumberPerColor("Game 12: 1 red, 5 blue 1 green, ;; 5 red, 3 blue, 6 green;")
        assertEquals(Triple(5,6,5), res)
    }

    @Test
    fun `sum the gameIds for possible games is calculated correctly`() {
        val res = cubeConundrum.executePart1("day2-part1-test-input", 12, 13, 14)
        assertEquals(8, res.first)
    }

    @Test
    fun `power of minimum set of cubes is calculated correctly`() {
        val res = cubeConundrum.executePart1("day2-part1-test-input", 12, 13, 14)
        assertEquals(2286, res.second)
    }
}