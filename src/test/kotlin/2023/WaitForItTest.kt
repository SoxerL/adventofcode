package `2023`

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WaitForItTest {
    private lateinit var waitForIt: WaitForIt

    @BeforeEach
    fun setup() {
        waitForIt = WaitForIt()
    }

    @Test
    fun `timRangeToBeatDistance returns correct range`() {
        val res = waitForIt.timeRangeToBeatDistance(7.0, 9)
        assertEquals(4, res)
    }

    @Test
    fun `execute returns correct number for part 1 of testInput`() {
        val res = waitForIt.execute("day6-part1-test-input")
        assertEquals(288L, res.first)
    }

    @Test
    fun `execute returns correct number for part 2 of testInput`() {
        val res = waitForIt.execute("day6-part1-test-input")
        assertEquals(71503L, res.second)
    }
}