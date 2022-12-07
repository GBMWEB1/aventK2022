import old.Day7
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun testPart1S() {
        val data = Util().readData("day8-1.txt")
        assertEquals(0, Day8().getPart1(data))
    }

    @Test
    fun testPart1() {
        val data = Util().readData("day8-2.txt")
        assertEquals(0, Day8().getPart1(data))
    }

    @Test
    fun testPart2S() {
        val data = Util().readData("day8-1.txt")

        assertEquals(0, Day8().getPart2(data))
    }

    @Test
    fun testPart2() {
        val data = Util().readData("day8-2.txt")

        assertEquals(0, Day8().getPart2(data))
    }
}
