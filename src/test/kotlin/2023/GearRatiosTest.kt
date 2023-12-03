package `2023`

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GearRatiosTest {
    private lateinit var gearRatios: GearRatios

    @BeforeEach
    fun setup() {
        gearRatios = GearRatios()
    }

    @Test
    fun `findBounds returns correct bounds`() {
        var res = gearRatios.findBounds("1234", 3)
        assertEquals(Pair(0, 4), res)
        res = gearRatios.findBounds("1234", 0)
        assertEquals(Pair(0, 4), res)
        res = gearRatios.findBounds("12.3", 1)
        assertEquals(Pair(0, 2), res)
        res = gearRatios.findBounds("12.3", 3)
        assertEquals(Pair(2, 4), res)
    }


    @Test
    fun `execute returns correct sum for part 1 of test input`() {
        val res = gearRatios.execute("day3-part1-test-input")
        assertEquals(4361, res.first)
    }

    @Test
    fun `returned sum of GearRatio is correct for test Input`() {
        var res = gearRatios.execute("day3-part1-test-input")
        assertEquals(467835, res.second)
        res = gearRatios.execute("day3-part2-test-input")
        assertEquals(78 * 78 + 12 * 56, res.second)
        res = gearRatios.execute("day3-part2-test-input-2")
        assertEquals(442, res.second)
    }
}