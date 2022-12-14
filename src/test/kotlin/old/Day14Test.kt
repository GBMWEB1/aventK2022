package old

import Util
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day14Test {

    //498,4 -> 498,6 -> 496,6

    @Test
    fun testDrawSingle(){
        val cave = Day14.Cave()
        cave.addPath("498,4 -> 498,6 -> 496,6")
        assertEquals(496, cave.maxleft)
        assertEquals(500, cave.maxRight)
        assertEquals(6, cave.bottomCave)
        assertEquals(listOf(
            "....+",
            ".....",
            ".....",
            ".....",
            "..#..",
            "..#..",
            "###.."), cave.getDisplay())
    }

    @Test
    fun testDrawMultiple(){
        val cave = Day14.Cave()
        cave.addPath("498,4 -> 498,6 -> 496,6")
        cave.addPath("503,4 -> 502,4 -> 502,9 -> 494,9")

        assertEquals(494, cave.maxleft)
        assertEquals(503, cave.maxRight)
        assertEquals(9, cave.bottomCave)
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
        "#########."), cave.getDisplay())
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
            "#########."), cave.getDisplay())
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
            "#########."), cave.getDisplay())
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
            "#########."), cave.getDisplay())
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
        "#########."), cave.getDisplay())
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

        assertEquals(11, cave.bottomCave)
        assertEquals(494-6, cave.maxleft)
        assertEquals(503+9, cave.maxRight)
        assertEquals(result,cave.getDisplay())
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
