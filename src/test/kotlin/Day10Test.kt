import old.Day9
import old.Day9.Pos

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day10Test {


    @Test
    fun testNoop(){
        val cpu = Day10.CPU(listOf("noop"))
        cpu.cycle()
        assertEquals(1, cpu.x)
        assertEquals(1, cpu.cyclesExecuted)
    }

    @Test
    fun testSecondCycle(){
        val cpu = Day10.CPU(listOf("noop","addx 3"))
        cpu.cycle(2)
        assertEquals(1, cpu.x)
        assertEquals(2, cpu.cyclesExecuted)
    }

    @Test
    fun testThirdCycle(){
        val cpu = Day10.CPU(listOf("noop","addx 3"))
        cpu.cycle(3)
        assertEquals(4, cpu.x)
        assertEquals(3, cpu.cyclesExecuted)
    }

    @Test
    fun testFithCycle(){
        val cpu = Day10.CPU(listOf("noop","addx 3", "addx -5"))
        cpu.cycle(5)

        assertEquals(-1, cpu.x)
        assertEquals(5, cpu.cyclesExecuted)
    }

    @Test
    fun testLargeSample(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle(20)
        assertEquals(420, cpu.signalStrength())

        cpu.cycle(40)
        assertEquals(1140, cpu.signalStrength())

        cpu.cycle(40)
        assertEquals(1800, cpu.signalStrength())

        cpu.cycle(40)
        assertEquals(2940, cpu.signalStrength())

        cpu.cycle(40)
        assertEquals(2880, cpu.signalStrength())

        cpu.cycle(40)
        assertEquals(3960, cpu.signalStrength())
    }

//    @Test
//    fun testPart1() {
//        val data = Util().readData("day9-1.txt")
//        val rope = Day9.Rope(9)
//        rope.init(headStart = Pos(1000,1000), Pos(1000,1000))
//        data.forEach({rope.move(it)})
//
//        assertEquals(2541, rope.positions.size)
//    }


}
