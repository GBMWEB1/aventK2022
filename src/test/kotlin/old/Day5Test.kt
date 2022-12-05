package old

import Util
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun testBuildQueue(){
        val input  = listOf(
            "[N]",
            "[Z]",
            " 1 "
        )
        assertEquals(ArrayDeque(listOf('Z','N')), Day5.BuildQueue(input).queues[0])
    }

    @Test
    fun testBuildQueueWithSample(){
        val data = Util().readData("day5-1.txt")

        val expectedResult : List<ArrayDeque<Char>> = listOf(
            ArrayDeque(listOf('Z','N')),
            ArrayDeque(listOf('M','C', 'D')),
            ArrayDeque(listOf('P')))
        assertEquals(expectedResult, Day5.BuildQueue(data).queues)
    }

    @Test
    fun testPart1WithSample() {
        val data = Util().readData("day5-1.txt")
        assertEquals("CMZ", Day5().part1(data))
    }

    @Test
    fun testPart1() {
        val data = Util().readData("day5-2.txt")
        assertEquals("SPFMVDTZT", Day5().part1(data))
    }

    @Test
    fun testPart2WithSample() {
        val data = Util().readData("day5-1.txt")
        assertEquals("MCD", Day5().part2(data))
    }

    @Test
    fun testPart2() {
        val data = Util().readData("day5-2.txt")
        assertEquals("ZFSJBPRFP", Day5().part2(data))
    }

}
