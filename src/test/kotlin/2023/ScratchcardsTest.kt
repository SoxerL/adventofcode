package `2023`

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ScratchcardsTest {
    private lateinit var scratchcards: Scratchcards

    @BeforeEach
    fun setup() {
        scratchcards = Scratchcards()
    }

    @Test
    fun `parseInput returns correct numbers`() {
        val res = scratchcards.parseInput("41 48 83 86 17 | 83 86  6 31 17  9 48 53")
        assertContentEquals(listOf(41, 48, 83, 86, 17), res.first)
        assertContentEquals(listOf(83, 86, 6, 31, 17, 9, 48, 53), res.second)
    }

    @Test
    fun `execute returns correct value for part 1 of testInput`() {
        val res = scratchcards.execute("day4-part1-test-input")
        assertEquals(13, res.first)
    }

    @Test
    fun `execute returns correct value for part 2 of testInput`() {
        val res = scratchcards.execute("day4-part1-test-input")
        assertEquals(30, res.second)
    }
}