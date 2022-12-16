package old

import Util
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day14Test {

    @Test
    fun testDrawSingle(){
        val cave = Day14.Cave()
        cave.addPath("498,4 -> 498,6 -> 496,6")
        assertEquals(496, cave.grid.maxLeft)
        assertEquals(500, cave.grid.maxRight)
        assertEquals(6, cave.grid.getBottom())
        assertEquals(listOf(
            "....+",
            ".....",
            ".....",
            ".....",
            "..#..",
            "..#..",
            "###.."), cave.grid.getDisplay())
    }

    @Test
    fun testDrawMultiple(){
        val cave = Day14.Cave()
        cave.addPath("498,4 -> 498,6 -> 496,6")
        cave.addPath("503,4 -> 502,4 -> 502,9 -> 494,9")

        assertEquals(494, cave.grid.maxLeft)
        assertEquals(503, cave.grid.maxRight)
        assertEquals(9, cave.grid.getBottom())
        assertEquals(listOf(
        "......+...",
        "..........",
        "..........",
        "..........",
        "....#...##",
        "....#...#.",
        "..###...#.",
        "........#.",
        "........#.",
        "#########."), cave.grid.getDisplay())
    }

    @Test
    fun testDropSingle(){
        val cave = Day14.Cave()
        cave.addPath("498,4 -> 498,6 -> 496,6")
        cave.addPath("503,4 -> 502,4 -> 502,9 -> 494,9")
        cave.dropSand()
        assertEquals(listOf(
            "......+...",
            "..........",
            "..........",
            "..........",
            "....#...##",
            "....#...#.",
            "..###...#.",
            "........#.",
            "......o.#.",
            "#########."), cave.grid.getDisplay())
    }

    @Test
    fun testDropTwo(){
        val cave = Day14.Cave()
        cave.addPath("498,4 -> 498,6 -> 496,6")
        cave.addPath("503,4 -> 502,4 -> 502,9 -> 494,9")
        cave.dropSand(2)

        assertEquals(listOf(
            "......+...",
            "..........",
            "..........",
            "..........",
            "....#...##",
            "....#...#.",
            "..###...#.",
            "........#.",
            ".....oo.#.",
            "#########."), cave.grid.getDisplay())
    }

    @Test
    fun testDropFive(){
        val cave = Day14.Cave()
        cave.addPath("498,4 -> 498,6 -> 496,6")
        cave.addPath("503,4 -> 502,4 -> 502,9 -> 494,9")
        cave.dropSand(5)

        assertEquals(listOf(
            "......+...",
            "..........",
            "..........",
            "..........",
            "....#...##",
            "....#...#.",
            "..###...#.",
            "......o.#.",
            "....oooo#.",
            "#########."), cave.grid.getDisplay())
    }

    @Test
    fun testDrop22(){
        val cave = Day14.Cave()
        cave.addPath("498,4 -> 498,6 -> 496,6")
        cave.addPath("503,4 -> 502,4 -> 502,9 -> 494,9")
        cave.dropSand(22)

        assertEquals(listOf(
        "......+...",
        "..........",
        "......o...",
        ".....ooo..",
        "....#ooo##",
        "....#ooo#.",
        "..###ooo#.",
        "....oooo#.",
        "...ooooo#.",
        "#########."), cave.grid.getDisplay())
    }

    @Test
    fun testPart1Sample(){
        val cave = Day14.Cave()
        cave.addPath("498,4 -> 498,6 -> 496,6")
        cave.addPath("503,4 -> 502,4 -> 502,9 -> 494,9")
        assertEquals(24,  cave.dropUntilEnd())
    }

    @Test
    fun testPart1(){
        val cave = Day14.Cave()
        for (line in Util().readData("day14.txt")){
            cave.addPath(line)
        }
        assertEquals(888,  cave.dropUntilEnd())
    }

    ////////////
    // Part 2 //
    ////////////
    @Test
    fun testAddFloor(){
        val cave = Day14.Cave()
        cave.addPath("498,4 -> 498,6 -> 496,6")
        cave.addPath("503,4 -> 502,4 -> 502,9 -> 494,9")
        cave.addFloor()

        val result= listOf(
        "............+............",
        ".........................",
        ".........................",
        ".........................",
        "..........#...##.........",
        "..........#...#..........",
        "........###...#..........",
        "..............#..........",
        "..............#..........",
        "......#########..........",
        ".........................",
        "#########################")

        assertEquals(11, cave.grid.getBottom())
        assertEquals(494-6, cave.grid.maxLeft)
        assertEquals(503+9, cave.grid.maxRight)
        assertEquals(result,cave.grid.getDisplay())
    }

    @Test
    fun testFillCave() {
        val cave = Day14.Cave()
        cave.addPath("498,4 -> 498,6 -> 496,6")
        cave.addPath("503,4 -> 502,4 -> 502,9 -> 494,9")
        cave.addFloor()
        assertEquals(93, cave.dropUntilEnd())
    }

    @Test
    fun testPart2(){
        val cave = Day14.Cave()
        for (line in Util().readData("day14.txt")){
            cave.addPath(line)
        }
        cave.addFloor()
        assertEquals(26461,  cave.dropUntilEnd())
    }

}
