import Day19.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day19Test {

    @Test
    fun testBluePrint1(){
        assertEquals(9, Day19().findMaxObs(BluePrint(), 24))
    }

    @Test
    fun testBluePrint2(){
        val bluePrint = BluePrint(
            oreRore = 2,
            clayRore = 3,
            obsRore = 3,
            obsRclay= 8,
            geoRore = 3,
            geoRobs= 12)
        assertEquals(12, Day19().findMaxObs(bluePrint, 24))
    }

    @Test
    fun testCreateBluePrints(){
        val bluePrints = BluePrint.list(Util().readData("day19.txt"))
        assertEquals(2, bluePrints.size)
    }

    @Test
    fun testCreateBluePrints2(){
        val bluePrints = BluePrint.list(Util().readData("day19-2.txt"))
        assertEquals(30, bluePrints.size)
    }

    @Test
    fun testValueForSamplePart1(){
        val bluePrints = BluePrint.list(Util().readData("day19.txt"))
        assertEquals(33, Day19().calculateMaxForBlueprints(bluePrints))
    }

    @Test
    fun testValueForPart1(){
        val bluePrints = BluePrint.list(Util().readData("day19-2.txt"))
        assertEquals(1023, Day19().calculateMaxForBlueprints(bluePrints))
    }

    // Part 2
    @Test
    fun testBluePrint1Part2(){
        assertEquals(56, Day19().findMaxObs(BluePrint(), 32))
    }

    @Test
    fun testBluePrint2Part2(){
        val bluePrint = BluePrint(
            oreRore = 2,
            clayRore = 3,
            obsRore = 3,
            obsRclay= 8,
            geoRore = 3,
            geoRobs= 12)
        assertEquals(62, Day19().findMaxObs(bluePrint, 32))
    }
    @Test
    fun testValueForPart2(){
        val bluePrints = BluePrint.list(Util().readData("day19-2.txt").take(3))

        assertEquals(26, Day19().findMaxObs(bluePrints[0], 32))
        assertEquals(52, Day19().findMaxObs(bluePrints[1], 32))
        assertEquals(10, Day19().findMaxObs(bluePrints[2], 32))
    }
}
