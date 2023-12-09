package `2023`

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MirageMaintenanceTest {

    private lateinit var mirageMaintenance: MirageMaintenance

    @BeforeEach
    fun setup() {
        mirageMaintenance = MirageMaintenance()
    }

    @Test
    fun `extraPolateSequence returns correct value for next Element`() {
        var res = mirageMaintenance.extraPolateSequence(listOf(0, 3, 6, 9, 12, 15))
        assertEquals(18, res.second)
        res = mirageMaintenance.extraPolateSequence(listOf(0, 3, 6, 9, 12, 15, 18))
        assertEquals(21, res.second)
        res = mirageMaintenance.extraPolateSequence(listOf(1, 3, 6, 10, 15, 21))
        assertEquals(28, res.second)
        res = mirageMaintenance.extraPolateSequence(listOf(10, 13, 16, 21, 30, 45))
        assertEquals(68, res.second)
    }

    @Test
    fun `extraPolateSequence returns correct value for previous element`() {
        var res = mirageMaintenance.extraPolateSequence(listOf(0, 3, 6, 9, 12, 15))
        assertEquals(-3, res.first)
        res = mirageMaintenance.extraPolateSequence(listOf(-3, 0, 3, 6, 9, 12, 15, 18))
        assertEquals(-6, res.first)
        res = mirageMaintenance.extraPolateSequence(listOf(1, 3, 6, 10, 15, 21))
        assertEquals(0, res.first)
        res = mirageMaintenance.extraPolateSequence(listOf(10, 13, 16, 21, 30, 45))
        assertEquals(5, res.first)
    }

    @Test
    fun `execute returns correct sum for nextElement in test input`() {
        val res = mirageMaintenance.execute("day9-part1-test-input")
        assertEquals(114, res.second)
    }

    @Test
    fun `execute returns correct sum for previousElement in test input`() {
        val res = mirageMaintenance.execute("day9-part1-test-input")
        assertEquals(2, res.first)
    }
}