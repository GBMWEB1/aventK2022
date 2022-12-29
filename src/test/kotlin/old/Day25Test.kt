package old

import Util
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day25Test {

    @Test
    fun testToDecimal() {
        assertEquals(1747, Day25().toDecimal("1=-0-2"))
        assertEquals(906, Day25().toDecimal("12111"))
        assertEquals(198, Day25().toDecimal("2=0="))
        assertEquals(11, Day25().toDecimal("21"))
        assertEquals(201, Day25().toDecimal("2=01"))
        assertEquals(31, Day25().toDecimal("111"))
        assertEquals(1257, Day25().toDecimal("20012"))
        assertEquals(32, Day25().toDecimal("112"))
        assertEquals(353, Day25().toDecimal("1=-1="))
    }

    @Test
    fun testToSumDecimal() {
        val input = listOf(
            "1=-0-2",
            "12111",
            "2=0=",
            "21",
            "2=01",
            "111",
            "20012",
            "112",
            "1=-1=",
            "1-12",
            "12",
            "1=",
            "122"
        )
        assertEquals(4890, Day25().sumDecimal(input))
        assertEquals("2=-1=0", Day25().toSpecial(4890))
    }

    @Test
    fun testToSpecial(){
        assertEquals("1", Day25().toSpecial(1))
        assertEquals("2", Day25().toSpecial(2))
        assertEquals("1=", Day25().toSpecial(3))
        assertEquals("1-", Day25().toSpecial(4))
        assertEquals("10", Day25().toSpecial(5))
        assertEquals("11", Day25().toSpecial(6))
        assertEquals("12", Day25().toSpecial(7))
        assertEquals("2=", Day25().toSpecial(8))
        assertEquals("2-", Day25().toSpecial(9))
        assertEquals("20", Day25().toSpecial(10))
        assertEquals("1=0", Day25().toSpecial(15))
        assertEquals("1-0", Day25().toSpecial(20))
        assertEquals("1=11-2", Day25().toSpecial(2022))
        assertEquals("1-0---0", Day25().toSpecial(12345))
        assertEquals("1121-1110-1=0", Day25().toSpecial(314159265))
    }

    @Test
    fun testPart1(){
        assertEquals(28927640190471, Day25().sumDecimal(Util().readData("day25.txt")))
        assertEquals("2==0=0===02--210---1", Day25().toSpecial(28927640190471))
    }
}
