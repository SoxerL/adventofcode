package `2023`

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PipeMazeTest {
    private lateinit var pipeMaze: PipeMaze

    @BeforeEach
    fun setup() {
        pipeMaze = PipeMaze()
    }

    @Test
    fun `execute returns correct distance for test input`() {
        var res = pipeMaze.execute("day10-part1-test-input")
        assertEquals(4, res.first)
        res = pipeMaze.execute("day10-part1-test-input-1-1")
        assertEquals(4, res.first)
        res = pipeMaze.execute("day10-part1-test-input-2")
        assertEquals(8, res.first)
        res = pipeMaze.execute("day10-part1-test-input-2-2")
        assertEquals(8, res.first)
    }

    @Test
    fun `execute returns correct day for part 2 test input`() {
        var res = pipeMaze.execute("day10-part2-test-input")
        assertEquals(4, res.second)
        res = pipeMaze.execute("day10-part2-test-input-2")
        assertEquals(8, res.second)
        res = pipeMaze.execute("day10-part2-test-input-3")
        assertEquals(10, res.second)
    }
}