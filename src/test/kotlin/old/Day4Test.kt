package old

import Util
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun testContains(){
        assertEquals(true, Day4().match(setOf(2,3),setOf(1,2,3,4)))
        assertEquals(true, Day4().match(setOf(1,2,3,4),setOf(2,3)))
    }

    @Test
    fun testNotContains(){
        assertEquals(false, Day4().match(setOf(1,2,3,4),setOf(2,3,4,5)))
    }

    @Test
    fun testOverlapSections(){
        assertEquals(true, Day4().overlap("2-8,3-7"))
        assertEquals(false, Day4().overlap("2-4,6-8"))
    }

    @Test
    fun testExampleCh1() {
        val data = Util().readData("day4-1.txt")
        assertEquals(2, Day4().countOverlaps(data))
    }

    @Test
    fun testCh1() {
        val data = Util().readData("day4-2.txt")
        assertEquals(571, Day4().countOverlaps(data))
    }

    @Test
    fun testAnyMatch() {
        assertEquals(true, Day4().anyMatch(setOf(1,2), setOf(2,3,4)))
        assertEquals(false, Day4().anyMatch(setOf(1,2), setOf(3,4)))
    }

    @Test
    fun testExampleCh2() {
        val data = Util().readData("day4-1.txt")
        assertEquals(4, Day4().countAnyOverlaps(data))
    }

    @Test
    fun testCh2() {
        val data = Util().readData("day4-2.txt")
        assertEquals(917, Day4().countAnyOverlaps(data))
    }
}
