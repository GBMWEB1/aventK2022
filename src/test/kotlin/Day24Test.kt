import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day24Test {

    @Test
    fun testInitial(){
        val start = listOf(
        "#.#####",
        "#..^..#",
        "#><...#",
        "#.....#",
        "#...v.#",
        "#.....#",
        "#####.#")
        val valley = Day24.Valley(start)
        valley.moveBlizzards()
        valley.moveBlizzards()
        valley.moveBlizzards()
        valley.moveBlizzards()
        valley.moveBlizzards()
    }

    @Test
    fun sampleNavigate(){
        val valley = Day24.Valley(Util().readData("day24.txt"))
        assertEquals(18, valley.navigateToEnd())
        assertEquals(23, valley.navigateToStart())
        assertEquals(13, valley.navigateToEnd())
    }

    @Test
    fun part2(){
        val valley = Day24.Valley(Util().readData("day24-2.txt"))
        assertEquals(274, valley.navigateToEnd())
        assertEquals(294, valley.navigateToStart())
        assertEquals(271, valley.navigateToEnd())
    }
}
