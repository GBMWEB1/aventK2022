package old

import Util
import old.Day15.Sensor.Companion.of
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class Day15Test {

    @Test
    fun testCreate() {
        val result = of("Sensor at x=2, y=18: closest beacon is at x=-2, y=15")
        assertEquals(2, result.x)
        assertEquals(18, result.y)
        assertEquals(-2, result.beacon.x)
        assertEquals(15, result.beacon.y)
    }

    @Test
    fun testDistance() {
        assertEquals(3, of("Sensor at x=2, y=18: closest beacon is at x=2, y=15").distance())

        assertEquals(4, of("Sensor at x=0, y=0: closest beacon is at x=4, y=0").distance())
        assertEquals(4, of("Sensor at x=0, y=0: closest beacon is at x=-4, y=0").distance())
        assertEquals(4, of("Sensor at x=0, y=0: closest beacon is at x=0, y=4").distance())
        assertEquals(4, of("Sensor at x=0, y=0: closest beacon is at x=0, y=-4").distance())

        assertEquals(8, of("Sensor at x=0, y=0: closest beacon is at x=4, y=-4").distance())
        assertEquals(9, of("Sensor at x=8, y=7: closest beacon is at x=2, y=10").distance())
    }

    @Test
    fun testIsInArea() {
        val signal = of("Sensor at x=8, y=7: closest beacon is at x=2, y=10")
        assertFalse(signal.isInArea(0,0))
        assertEquals(true, signal.isInArea(8,-2))
        assertEquals(false, signal.isInArea(8,-3))
        assertEquals(false, signal.isInArea(8,-3))

        // x of 6, y = 0 is in area
        assertEquals(true, signal.isInArea(6,0))
        assertEquals(false, signal.isInArea(5,0))
        assertEquals(false, signal.isInArea(6,-1))
    }

    @Test
    fun testIsBeacon() {
        val signal = of("Sensor at x=8, y=7: closest beacon is at x=2, y=10")
        assertEquals(true, signal.isBeacon(2,10))
    }


    @Test
    fun testDisplay() {
        val grid = Day15.Grid(listOf("Sensor at x=8, y=7: closest beacon is at x=2, y=10"))
        grid.display()
    }

    @Test
    fun testDisplaySample() {
        val grid = Day15.Grid(Util().readData("day15.txt"))
        grid.display()
    }

    @Test
    fun testPart1Sample() {
        assertEquals(26, Day15.Grid(Util().readData("day15.txt")).countCoverage(10))

    }

    @Test
    fun testPart1() {
        assertEquals(4961647, Day15.Grid(Util().readData("day15-2.txt")).countCoverage(2000000))
    }

    // part 2

    @Test
    fun testPart2Sample(){
        assertEquals(56000011, Day15.Grid(Util().readData("day15.txt")).findDistressBeacon(20))
    }

    @Test
    fun testPart2() {
        assertEquals(12274327017867, Day15.Grid(Util().readData("day15-2.txt")).findDistressBeacon(4000000))
    }
}
