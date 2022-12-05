import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun testPart1WithSample() {
        val data = Util().readData("day6-1.txt")
        assertEquals("Hello", Day6().part1(data))
    }

    @Test
    fun testPart1() {
        val data = Util().readData("day6-2.txt")
        assertEquals("World", Day6().part1(data))
    }

    @Test
    fun testPart2WithSample() {
        val data = Util().readData("day6-1.txt")
        assertEquals("Hello", Day6().part2(data))
    }

    @Test
    fun testPart2() {
        val data = Util().readData("day6-2.txt")
        assertEquals("World", Day6().part2(data))
    }
}
