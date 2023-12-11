package `2023`

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class CosmicExpansionTest {

    private lateinit var cosmicExpansion: CosmicExpansion

    @BeforeEach
    fun setup() {
        cosmicExpansion = CosmicExpansion()
    }

    @Test
    fun `transpose changes matrix correctly`() {
        val res = cosmicExpansion.transpose(listOf("123", "456"))
        assertContentEquals(listOf("14", "25", "36"), res)
    }

    @Test
    fun `findEmptyRowsAndCols finds all empty Rows and Cols`() {
        val lines = this::class.java.getResourceAsStream("day11-part1-test-input")?.bufferedReader()?.readLines()!!
        val res = cosmicExpansion.findEmptyRowsAndCols(lines)
        assertContentEquals(listOf(3L, 7L), res.first())
        assertContentEquals(listOf(2L, 5L, 8L), res.last())
    }

    @Test
    fun `execute returns correct sum for part 1 of test input`() {
        val res = cosmicExpansion.execute("day11-part1-test-input")
        assertEquals(374L, res.first)
    }

    @Test
    fun `execute returns correct sum for part 2 of test input`() {
        val res = cosmicExpansion.execute("day11-part1-test-input")
        assertEquals(82000210L, res.second)
    }
}