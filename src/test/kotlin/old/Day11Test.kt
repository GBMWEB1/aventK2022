package old

import Util
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Day11Test {

    @Test
    fun testMonkeyCount() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"))
        assertEquals(4, monkeys.size)
    }

    @Test
    fun testParseMonkeyItems() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"))
        assertEquals(listOf(79,98), monkeys[0].getItemsInt())
    }

    @Test
    fun testParseMonkeyOperation() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"))
        assertEquals(Day11.Operation("old * 19"), monkeys[0].operation)
    }

    @Test
    fun testParseMonkeyTestDiv() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"))
        assertEquals(BigInteger.valueOf(23), monkeys[0].testDiv)
    }

    @Test
    fun testParseMonkeyTrueIndex() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"))
        assertEquals(2, monkeys[0].monkeyTrueIndex)
    }

    @Test
    fun testParseMonkeyFalseIndex() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"))
        assertEquals(3, monkeys[0].monkeyFalseIndex)
    }

    @Test
    fun testOperationTimesN() {
        assertEquals(38, Day11.Operation("old * 19").performInt(2))
    }

    @Test
    fun testOperationPlusN() {
        assertEquals(21, Day11.Operation("old + 19").performInt(2))
    }

    @Test
    fun testOperationSquare() {
        assertEquals(9, Day11.Operation("old * old").performInt(3))
    }

    @Test
    fun testBored() {
        assertEquals(BigInteger.valueOf(500), Day11.Monkey.getBored(BigInteger.valueOf(1501)))
        assertEquals(BigInteger.valueOf(620), Day11.Monkey.getBored(BigInteger.valueOf(1862)))
        assertEquals(BigInteger.valueOf(20), Day11.Monkey.getBored(BigInteger.valueOf(60)))
    }

    @Test
    fun testRound1() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"))
        Day11().processRound(monkeys);

        assertEquals(listOf(20,23,27,26), monkeys[0].getItemsInt())
        assertEquals(listOf(2080,25,167,207,401, 1046), monkeys[1].getItemsInt())
        assertEquals(listOf<Int>(), monkeys[2].getItemsInt())
        assertEquals(listOf<Int>(), monkeys[3].getItemsInt())
    }

    @Test
    fun testRound20() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"))
        Day11().processRounds(20, monkeys);

        assertEquals(listOf<Int>(10, 12, 14, 26, 34), monkeys[0].getItemsInt())
        assertEquals(listOf<Int>(245, 93, 53, 199, 115), monkeys[1].getItemsInt())
        assertEquals(listOf<Int>(), monkeys[2].getItemsInt())
        assertEquals(listOf<Int>(), monkeys[3].getItemsInt())
    }
    @Test
    fun testRound20ItemsProcessed() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"))
        Day11().processRounds(20, monkeys);

        assertEquals(101, monkeys[0].itemsProcessed)
        assertEquals(95, monkeys[1].itemsProcessed)
        assertEquals(7, monkeys[2].itemsProcessed)
        assertEquals(105, monkeys[3].itemsProcessed)
    }

    @Test
    fun testRound20MonkeyBusiness() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"))
        Day11().processRounds(20, monkeys);
        assertEquals(10605, Day11().getMonkeyBusiness(monkeys))
    }

    @Test
    fun testPart1() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-2.txt"))
        Day11().processRounds(20, monkeys);
        assertEquals(54752, Day11().getMonkeyBusiness(monkeys))
    }

    @Test
    fun testPart2Round1() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"), false)
        Day11().processRounds(1, monkeys);

        assertEquals(2, monkeys[0].itemsProcessed)
        assertEquals(4, monkeys[1].itemsProcessed)
        assertEquals(3, monkeys[2].itemsProcessed)
        assertEquals(6, monkeys[3].itemsProcessed)
    }

    @Test
    fun testPart2Round20() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"), false)
        Day11().processRounds(20, monkeys);

        assertEquals(99, monkeys[0].itemsProcessed)
        assertEquals(97, monkeys[1].itemsProcessed)
        assertEquals(8, monkeys[2].itemsProcessed)
        assertEquals(103, monkeys[3].itemsProcessed)
    }

    @Test
    fun testPart2Round1000() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"), false)
        Day11().processRounds(1000, monkeys);

        assertEquals(5204, monkeys[0].itemsProcessed)
        assertEquals(4792, monkeys[1].itemsProcessed)
        assertEquals(199, monkeys[2].itemsProcessed)
        assertEquals(5192, monkeys[3].itemsProcessed)
    }

    @Test
    fun testPart2Round10000() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-1.txt"), false)
        Day11().processRounds(10000, monkeys);

        assertEquals(52166, monkeys[0].itemsProcessed)
        assertEquals(47830, monkeys[1].itemsProcessed)
        assertEquals(1938, monkeys[2].itemsProcessed)
        assertEquals(52013, monkeys[3].itemsProcessed)
        assertEquals(2713310158, Day11().getMonkeyBusiness(monkeys))
    }

    @Test
    fun testPart2() {
        val monkeys = Day11().createMonkeys(Util().readData("day11-2.txt"), false)
        Day11().processRounds(10000, monkeys);
        assertEquals(13606755504, Day11().getMonkeyBusiness(monkeys))
    }
}
