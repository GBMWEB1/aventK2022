package old

import old.Day9.Pos
import Util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day9Test {

    @Test
    fun testInitial() {
        val rope = Day9.Rope()
        rope.init()
        assertEquals(Pos(0, 0), rope.getHeadPosition())
        assertEquals(Pos(0, 0), rope.getTailPosition())
        assertEquals(setOf(Pos(0, 0)), rope.positions)
    }

    @Test
    fun testMoveRight() {
        val rope = Day9.Rope(2, true) // initial pos
        rope.init()
        rope.move("R 4")
        assertEquals(Pos(4, 0), rope.getHeadPosition())
        assertEquals(Pos(3, 0), rope.getTailPosition())
        assertEquals(setOf(
            Pos(0, 0),
            Pos(1, 0),
            Pos(2, 0),
            Pos(3, 0),
        ), rope.positions)
    }

    @Test
    fun testMoveUp() {
        val rope = Day9.Rope()
        rope.init(
            listOf(Pos(4, 0),Pos(3, 0))
        )

        rope.move("U 1")
        assertEquals(Pos(4,1), rope.getHeadPosition())
        assertEquals(Pos(3,0), rope.getTailPosition()) // tail not moved
        assertEquals(setOf(Pos(3, 0)), rope.positions)

        rope.move("U 1")
        assertEquals(Pos(4,2), rope.getHeadPosition())
        assertEquals(Pos(4,1), rope.getTailPosition()) // tail moved up and Right
        assertEquals(setOf(Pos(3, 0),Pos(4,1) ), rope.positions)

        rope.move("U 2")
        assertEquals(Pos(4,4), rope.getHeadPosition())
        assertEquals(Pos(4,3), rope.getTailPosition())
    }

    @Test
    fun testMoveLeft() {
        val rope = Day9.Rope(2, true)
        rope.init(listOf(
            Pos(4,4),
            Pos(4,3)
        ))
        rope.move("L 3")
        assertEquals(Pos(1,4), rope.getHeadPosition())
        assertEquals(Pos(2,4), rope.getTailPosition())
    }

    @Test
    fun testMoveDown() {
        val rope = Day9.Rope()
        rope.init(
            listOf(
                Pos(1,4),
                Pos(2,4)
            )
        )
        rope.move("D 1")
        assertEquals(Pos(1,3), rope.getHeadPosition())
        assertEquals(Pos(2,4), rope.getTailPosition())

        rope.move("D 1")
        assertEquals(Pos(1,2), rope.getHeadPosition())
        assertEquals(Pos(1,3), rope.getTailPosition())


    }

    @Test
    fun testSample(){
        val sampleData = listOf(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2"
        )
        val rope = Day9.Rope()
        rope.init()
        sampleData.forEach { rope.move(it) }
        assertEquals(13, rope.positions.size)
    }

    @Test
    fun testPart1() {
        val data = Util().readData("day9-1.txt")
        val rope = Day9.Rope()
        rope.init()
        data.forEach { rope.move(it) }
        assertEquals(6339, rope.positions.size)
    }

    @Test
    fun testSample1Part2(){
        val sampleData = listOf(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2"
        )
        val rope = Day9.Rope(9)
        rope.init()
        sampleData.forEach { rope.move(it) }
        assertEquals(1, rope.positions.size)
    }

    @Test
    fun testLargerSamplePart2(){
        val sampleData = listOf(
            "R 5",
            "U 8",
            "L 8",
            "D 3",
            "R 17",
            "D 10",
            "L 25",
            "U 20"
        )
        val rope = Day9.Rope(10)
        rope.init(
            listOf(
                Pos(11,5),
                Pos(11,5))
        )
        sampleData.forEach {
            rope.move(it)
        }
        assertEquals(36, rope.positions.size)
    }

    @Test
    fun testPart2() {
        val data = Util().readData("day9-1.txt")
        val rope = Day9.Rope(10)
        rope.init(listOf(
            Pos(1000,1000),
            Pos(1000,1000)))
        data.forEach { rope.move(it) }

        assertEquals(2541, rope.positions.size)
    }


}
