import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day18Test {

    @Test
    fun testCalculateSidesForSample() {
        val pond = Day18.Pond.of(Util().readData("day18.txt"))
        assertEquals(64, pond.calculateVisibleSides())
    }

    @Test
    fun testPart1() {
        val pond = Day18.Pond.of(Util().readData("day18-2.txt"))
        assertEquals(4418, pond.calculateVisibleSides())
    }

    // Part 2
    @Test
    fun testFindAirPocketsWithSample() {
        val pond = Day18.Pond.of(Util().readData("day18.txt"))
        pond.findAirPockets()
        assertEquals(listOf( Day18.Cube(2, 2, 5)), pond.airPockets)
    }

    @Test
    fun testSample(){
        val pond = Day18.Pond.of(Util().readData("day18.txt"))
        pond.findAirPockets()

        assertEquals(58, pond.calculateVisibleSides())
    }

    @Test
    fun testPart2(){
        val pond = Day18.Pond.of(Util().readData("day18-2.txt"))
        pond.findAirPockets()
        assertEquals(2486, pond.calculateVisibleSides())
    }

}
