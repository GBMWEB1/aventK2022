package old

import Util
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
        assertEquals(16, cpu.x)

        cpu.cycle(40)
        assertEquals(3960, cpu.signalStrength())
    }

    @Test
    fun testPart1(){
        val cpu = Day10.CPU(Util().readData("day10-3.txt"))
        cpu.cycle(20)
        val a = cpu.signalStrength()

        cpu.cycle(40)
        val b = cpu.signalStrength()

        cpu.cycle(40)
        val c = cpu.signalStrength()

        cpu.cycle(40)
        val d = cpu.signalStrength()

        cpu.cycle(40)
        val e = cpu.signalStrength()

        cpu.cycle(40)
        val f = cpu.signalStrength()

        assertEquals(14620, a+b+c+d+e+f)
    }

    ///////////
    // Part 2//
    ///////////
    @Test
    fun testCrt(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle()
        assertEquals("#", cpu.getCrtRow())
    }

    @Test
    fun testCrt2(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle(2)
        assertEquals("##", cpu.getCrtRow())
    }

    @Test
    fun testCrt3(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle(3)
        assertEquals("##.", cpu.getCrtRow())
    }

    @Test
    fun testCrt4(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle(4)
        assertEquals("##..", cpu.getCrtRow())
    }

    @Test
    fun testCrt5(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle(5)
        assertEquals("##..#", cpu.getCrtRow())
    }

    @Test
    fun testCrt6(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle(6)
        assertEquals("##..##", cpu.getCrtRow())
    }

    @Test
    fun testCrt7(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle(7)
        assertEquals("##..##.", cpu.getCrtRow())
    }

    @Test
    fun testCrt8(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle(8)
        assertEquals("##..##..", cpu.getCrtRow())
    }

    @Test
    fun testCrt9(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle(9)
        assertEquals("##..##..#", cpu.getCrtRow())
    }

    @Test
    fun testCrt10(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle(10)
        assertEquals("##..##..##", cpu.getCrtRow())
    }

    @Test
    fun testCrt11(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle(11)
        assertEquals("##..##..##.", cpu.getCrtRow())
    }

    @Test
    fun testCrt12(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle(12)
        assertEquals("##..##..##..", cpu.getCrtRow())
    }

    @Test
    fun testCrt21(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycle(21)
        assertEquals("##..##..##..##..##..#", cpu.getCrtRow())
    }

    @Test
    fun testCrtSample(){
        val cpu = Day10.CPU(Util().readData("day10-1.txt"))
        cpu.cycleUntilEnd()
        val expect = listOf(
            "##..##..##..##..##..##..##..##..##..##..",
            "###...###...###...###...###...###...###.",
            "####....####....####....####....####....",
            "#####.....#####.....#####.....#####.....",
            "######......######......######......####",
            "#######.......#######.......#######....."
        )
        assertEquals(expect, cpu.getCrtRows())
    }

    @Test
    fun testPart2() {
        val cpu = Day10.CPU(Util().readData("day10-3.txt"))
        cpu.cycleUntilEnd()
        cpu.getCrtRows().forEach { println(it) }
    }
}
