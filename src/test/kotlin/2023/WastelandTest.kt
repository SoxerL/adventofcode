package `2023`

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WastelandTest {

    private lateinit var wasteland: Wasteland

    @BeforeEach
    fun setup() {
        wasteland = Wasteland()
    }

    @Test
    fun `gcd returns correct result`() {
        var res = wasteland.gcd(48, 18)
        assertEquals(6, res)
        res = wasteland.gcd(18, 48)
        assertEquals(6, res)
    }

    @Test
    fun `lcm returns correct result`() {
        var res = wasteland.calculateLCM(3, 4)
        assertEquals(12, res)
        res = wasteland.calculateLCM(5, 3)
        assertEquals(15, res)
        res = wasteland.calculateLCM(7, 5)
        assertEquals(35, res)
        res = wasteland.calculateLCM(24, 18)
        assertEquals(72, res)
    }

    @Test
    fun `execute returns correct moveCounter for test input`() {
        var res = wasteland.executePart1("day8-part1-test-input")
        assertEquals(2, res)
        res = wasteland.executePart1("day8-part1-test-input-2")
        assertEquals(6, res)
    }

    @Test
    fun `execute returns correct moveCounter for part 2 test input`() {
        val res = wasteland.executePart2("day8-part2-test-input")
        assertEquals(6, res)
    }
}