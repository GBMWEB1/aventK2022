package old

import Util
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun test2Elves() {
        val data = listOf("1000","2000","3000","","4000")
        val result = Day1().calculateMaxCalories(data)
        assertEquals(6000, result)
    }

    @Test
    fun testCh1() {
        val data = Util().readData("day1-1.data")
        val result = Day1().calculateMaxCalories(data)
        assertEquals(70698, result)
    }

    @Test
    fun testTop3(){
        val data = listOf("1000","2000","3000","","4000","","5000","6000", "","7000","8000","9000","", "10000")
        val result = Day1().calculateTop3Cals(data)
        assertEquals(45000, result)
    }

    @Test
    fun testTop3Ch2(){
        val data = Util().readData("day1-1.data")
        val result = Day1().calculateTop3Cals(data)
        assertEquals(206643, result)
    }
}