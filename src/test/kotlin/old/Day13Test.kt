package old

import Util
import old.Day13.Answer
import old.Day13.Packet
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day13Test {

    @Test
    fun testPair1() {
        assertEquals(Answer.RIGHT_ORDER, Day13().compare(Packet("[1,1,3,1,1]"), Packet("[1,1,5,1,1]")))
    }

    @Test
    fun testPair2() {
        assertEquals(Answer.RIGHT_ORDER, Day13().compare(Packet("[[1],[2,3,4]]"), Packet("[[1],4]")))
    }

    @Test
    fun testPair3() {
        assertEquals(Answer.WRONG_ORDER, Day13().compare(
            Packet("[9]"), Packet("[[8,7,6]]")))
    }

    @Test
    fun testPair4() {
        assertEquals(Answer.RIGHT_ORDER, Day13().compare(
            Packet("[[4,4],4,4]"),
            Packet("[[4,4],4,4,4]")))
    }

    @Test
    fun testPair5() {
        assertEquals(Answer.WRONG_ORDER, Day13().compare(
            Packet("[7,7,7,7]"), Packet("[7,7,7]"))
        )
    }

    @Test
    fun testPair6() {
        assertEquals(Answer.RIGHT_ORDER, Day13().compare(
            Packet("[]"),
            Packet("[3]")))
    }

    @Test
    fun testPair7() {
        assertEquals(Answer.WRONG_ORDER,
            Day13().compare(Packet("[[[]]]"), Packet("[[]]")))
    }
    @Test
    fun testPair8() {
        assertEquals(Answer.WRONG_ORDER,
            Day13().compare(
                Packet("[1,[2,[3,[4,[5,6,7]]]],8,9]"),
                Packet("[1,[2,[3,[4,[5,6,0]]]],8,9]")))
    }

    @Test
    fun testPair9() {
        assertEquals(Answer.RIGHT_ORDER,
            Day13().compare(Packet("[]"), Packet("[[7],[0,4,6,1]]")))
    }

    @Test
    fun testSample() {
        val input = Util().readData("day13.txt")
        val pairs = input.filter { it.isNotEmpty() }.chunked(2)

        var sum = 0
        pairs.forEachIndexed { index, pair ->
            val pairNumber = index +1
            println("== Pair $pairNumber ==")
            if (Day13().isInRightOrder(pair[0], pair[1])){
                sum += index+1
            }
        }
        assertEquals(13, sum)
    }

    @Test
    fun testPart1() {
        val input = Util().readData("day13-2.txt")
        val pairs = input.filter { it.isNotEmpty() }.chunked(2)

        var sum = 0
        pairs.forEachIndexed { index, pair ->
            val pairNumber = index +1
            println("== Pair $pairNumber ==")
            if (Day13().isInRightOrder(pair[0], pair[1])){
                sum += index+1
            }
        }
        assertEquals(5208, sum)
    }

    @Test
    fun testPart2Sample() {
        val list = Util().readData("day13.txt")
            .filter { it.isNotEmpty() }.toMutableList()

        list.add("[2]")
        list.add("[6]")

        val sorted = list.sortedWith { o1, o2 -> Day13().isInRightOrderCompare(o1, o2) }

        println(sorted)

    }
    @Test
    fun testPart2() {
        val list = Util().readData("day13-2.txt")
            .filter { it.isNotEmpty() }.toMutableList()

        list.add("[2]")
        list.add("[6]")
        val sorted = list.sortedWith { o1, o2 -> Day13().isInRightOrderCompare(o1, o2) }

        val value = (sorted.indexOf("[2]")+1) * (sorted.indexOf("[6]")+1)

        println(sorted)
        assertEquals(25792, value)

    }
}
