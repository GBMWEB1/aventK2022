package old

import Util
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun testNavigateRight() {
        val grid = Day12.Grid(listOf("SyzE")).init()
        assertEquals(3, grid.navigateToEnd())
    }

    @Test
    fun testNavigateLeft() {
        val grid = Day12.Grid(listOf("EzyS")).init()
        assertEquals(3, grid.navigateToEnd())
    }

    @Test
    fun testNavigateTop() {
        val grid = Day12.Grid(listOf("E","z","y","S")).init()
        assertEquals(3, grid.navigateToEnd())
    }

    @Test
    fun testNavigateBottom() {
        val grid = Day12.Grid(listOf("S","y","z","E")).init()
        assertEquals(3, grid.navigateToEnd())
    }

    @Test
    fun testSample() {
        val grid = Day12.Grid(
            listOf(
                "Sabqponm",
                "abcryxxl",
                "accszExk",
                "acctuvwj",
                "abdefghi"))
            .init()
        assertEquals(31, grid.navigateToEnd())
    }


    @Test
    fun testPart1() {
        val grid = Day12.Grid(Util().readData("day12.txt"))
            .init()
        assertEquals(408, grid.navigateToEnd())
    }

    @Test
    fun testSampleP2() {
        val grid = Day12.Grid(
            listOf(
                "Sabqponm",
                "abcryxxl",
                "accszExk",
                "acctuvwj",
                "abdefghi"))
            .init()
        assertEquals(29, grid.navigateFromA())
    }

    @Test
    fun testPart2() {
        val grid = Day12.Grid(Util().readData("day12.txt"))
            .init()
        assertEquals(399, grid.navigateFromA())
    }
}
