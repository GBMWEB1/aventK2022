package old

import Util
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun testS1part1() {
        assertEquals(7, Day6().getMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb"))
    }

    @Test
    fun testS2part1() {
        assertEquals(5, Day6().getMarker("bvwbjplbgvbhsrlpgdmjqwftvncz"))
    }

    @Test
    fun testS3part1() {
        assertEquals(10, Day6().getMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))
    }

    @Test
    fun testPart1() {
        val data = Util().readData("day6-1.txt")
        assertEquals(1198, Day6().getMarker(data[0]))
    }

    @Test
    fun testS1part2() {
        assertEquals(19, Day6().getMarker("mjqjpqmgbljsphdztnvjfqwrcgsmlb",14))
    }

    @Test
    fun testS2part2() {
        assertEquals(23, Day6().getMarker("bvwbjplbgvbhsrlpgdmjqwftvncz",14))
    }

    @Test
    fun testS3part2() {
        assertEquals(29, Day6().getMarker("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",14))
    }

    @Test
    fun testPart2() {
        val data = Util().readData("day6-1.txt")
        assertEquals(3120, Day6().getMarker(data[0],14))
    }
}
