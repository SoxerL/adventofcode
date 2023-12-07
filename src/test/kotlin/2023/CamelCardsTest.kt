package `2023`

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CamelCardsTest {
    private lateinit var camelCards: CamelCards

    @BeforeEach
    fun setup() {
        camelCards = CamelCards()
    }

    @Test
    fun `determinType returns correct Type for hand input`() {
        var res = camelCards.determinType("32T3K")
        assertEquals(HandType.PAIR, res)
        res = camelCards.determinType("T55J5")
        assertEquals(HandType.THREEKIND, res)
        res = camelCards.determinType("KK677")
        assertEquals(HandType.TWOPAIR, res)
        res = camelCards.determinType("KTJJT")
        assertEquals(HandType.TWOPAIR, res)
        res = camelCards.determinType("QQQJA")
        assertEquals(HandType.THREEKIND, res)
        res = camelCards.determinType("QQQQQ")
        assertEquals(HandType.FIVEKIND, res)
        res = camelCards.determinType("QQQAQ")
        assertEquals(HandType.FOURKIND, res)
        res = camelCards.determinType("34A78")
        assertEquals(HandType.HIGHCARD, res)
        res = camelCards.determinType("QQQJJ")
        assertEquals(HandType.FULLHOUSE, res)
    }

    @Test
    fun `splitByType returns correct list of lists of items`() {
        val res = camelCards.splitByType(listOf(Pair("23456", 1), Pair("22345", 1), Pair("AKQJT", 1), Pair("6689T", 1)))
        assertEquals(2, res.size)
        assertEquals(2, res[0].size)
        assertEquals(2, res[1].size)
    }

    @Test
    fun `execute returns correct sum for part 1 of testInput`() {
        val res = camelCards.execute("day7-part1-test-input")
        assertEquals(6440, res.first)
    }

    @Test
    fun `determinTypeWithJokers returns correct type`() {
        var res = camelCards.determinTypeWithJokers("32T3K")
        assertEquals(HandType.PAIR, res)
        res = camelCards.determinTypeWithJokers("T55J5")
        assertEquals(HandType.FOURKIND, res)
        res = camelCards.determinTypeWithJokers("KK677")
        assertEquals(HandType.TWOPAIR, res)
        res = camelCards.determinTypeWithJokers("KTJJT")
        assertEquals(HandType.FOURKIND, res)
        res = camelCards.determinTypeWithJokers("QQQJA")
        assertEquals(HandType.FOURKIND, res)
        res = camelCards.determinTypeWithJokers("QQQQQ")
        assertEquals(HandType.FIVEKIND, res)
        res = camelCards.determinTypeWithJokers("QQQAQ")
        assertEquals(HandType.FOURKIND, res)
        res = camelCards.determinTypeWithJokers("34A78")
        assertEquals(HandType.HIGHCARD, res)
        res = camelCards.determinTypeWithJokers("QQQJJ")
        assertEquals(HandType.FIVEKIND, res)
        res = camelCards.determinTypeWithJokers("QQQAA")
        assertEquals(HandType.FULLHOUSE, res)
        res = camelCards.determinTypeWithJokers("QQQ23")
        assertEquals(HandType.THREEKIND, res)
        res = camelCards.determinTypeWithJokers("AJQ8K")
        assertEquals(HandType.PAIR, res)
        res = camelCards.determinTypeWithJokers("KKKKJ")
        assertEquals(HandType.FIVEKIND, res)
        res = camelCards.determinTypeWithJokers("AJJAJ")
        assertEquals(HandType.FIVEKIND, res)
        res = camelCards.determinTypeWithJokers("AAAA9")
        assertEquals(HandType.FOURKIND, res)
        res = camelCards.determinTypeWithJokers("AAKKJ")
        assertEquals(HandType.FULLHOUSE, res)
        res = camelCards.determinTypeWithJokers("AATA8")
        assertEquals(HandType.THREEKIND, res)
        res = camelCards.determinTypeWithJokers("AA2K2")
        assertEquals(HandType.TWOPAIR, res)
        res = camelCards.determinTypeWithJokers("AA94K")
        assertEquals(HandType.PAIR, res)
        res = camelCards.determinTypeWithJokers("AK736")
        assertEquals(HandType.HIGHCARD, res)
        res = camelCards.determinTypeWithJokers("2JJJJ")
        assertEquals(HandType.FIVEKIND, res)
        res = camelCards.determinTypeWithJokers("Q2KJJ")
        assertEquals(HandType.THREEKIND, res)
    }

    @Test
    fun `execute returns correct sum for part 2 of testInput`() {
        val res = camelCards.execute("day7-part1-test-input")
        assertEquals(5905, res.second)
    }

    @Test
    fun `execute returns correct sum for part 2 of testInput part2`() {
        val res = camelCards.execute("day7-part2-test-input")
        assertEquals(6839, res.second)
    }
}