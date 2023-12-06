package `2023`

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FertilizerTest {
    private lateinit var fertilizer: Fertilizer

    @BeforeEach
    fun setup() {
        fertilizer = Fertilizer()
    }

    @Test
    fun `execute finds the correct result for part 1 of test input`() {
        val res = fertilizer.execute("day5-part1-test-input")
        assertEquals(35, res.first)
    }

    @Test
    fun `execute finds the correct result for part 2 of test input`() {
        val res = fertilizer.execute("day5-part1-test-input")
        assertEquals(46, res.second)
    }
}