import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun testPart1CountEdges() {
        val data = Util().readData("day8-1.txt")
        assertEquals(16, Day8().countEdges(data))
    }

    @Test
    fun testBuildGrid() {
        val result = Day8.Grid(listOf("123","567","789"))
        assertEquals(1, result.getCell(0,0))
        assertEquals(5, result.getCell(1,0))
    }

    private val sampleData = listOf(
        "30373",
        "25512",
        "65332",
        "33549",
        "35390"
    )
    @Test
    fun testInnerLists() {
        assertEquals(listOf(5,5,2), Day8.Grid(sampleData).getLeft(1,3))
        assertEquals(listOf(2), Day8.Grid(sampleData).getRight(1,3))
        assertEquals(listOf(7), Day8.Grid(sampleData).getTop(1,3))
        assertEquals(listOf(3,4,9), Day8.Grid(sampleData).getBottom(1,3))
    }

    @Test
    fun testTopLeft5() {
        assertEquals(true, Day8.Grid(sampleData).isVisibleLeft(1,1))
        assertEquals(true, Day8.Grid(sampleData).isVisibleTop(1,1))
        assertEquals(false, Day8.Grid(sampleData).isVisibleRight(1,1))
        assertEquals(false, Day8.Grid(sampleData).isVisibleBottom(1,1))
    }
    @Test
    fun testTopMiddle5() {
        assertEquals(false, Day8.Grid(sampleData).isVisibleLeft(1,2))
        assertEquals(true, Day8.Grid(sampleData).isVisibleTop(1,2))
        assertEquals(true, Day8.Grid(sampleData).isVisibleRight(1,2))
        assertEquals(false, Day8.Grid(sampleData).isVisibleBottom(1,2))
    }

    @Test
    fun testTopRight1() {
        assertEquals(false, Day8.Grid(sampleData).isVisibleLeft(1,3))
        assertEquals(false, Day8.Grid(sampleData).isVisibleTop(1,3))
        assertEquals(false, Day8.Grid(sampleData).isVisibleRight(1,3))
        assertEquals(false, Day8.Grid(sampleData).isVisibleBottom(1,3))
    }

    @Test
    fun testLeftMiddle5() {
        assertEquals(false, Day8.Grid(sampleData).isVisibleLeft(2,1))
        assertEquals(false, Day8.Grid(sampleData).isVisibleTop(2,1))
        assertEquals(true, Day8.Grid(sampleData).isVisibleRight(2,1))
        assertEquals(false, Day8.Grid(sampleData).isVisibleBottom(2,1))
    }

    @Test
    fun testCenter() {
        assertEquals(false, Day8.Grid(sampleData).isVisibleLeft(2,2))
        assertEquals(false, Day8.Grid(sampleData).isVisibleTop(2,2))
        assertEquals(false, Day8.Grid(sampleData).isVisibleRight(2,2))
        assertEquals(false, Day8.Grid(sampleData).isVisibleBottom(2,2))
    }

    @Test
    fun testCountInterior() {
        assertEquals(1, Day8().countInterior(listOf("123","456","789")))
    }


    @Test
    fun testPart1CountInterior() {
        val data = Util().readData("day8-1.txt")
        assertEquals(5, Day8().countInterior(data))
    }

    @Test
    fun testPart1() {
        val data = Util().readData("day8-2.txt")
        assertEquals(1698, Day8().getPart1(data))
    }

    @Test
    fun testScenicScore() {
        val sampleData = listOf(
            "30373",
            "25512",
            "65332",
            "33549",
            "35390"
        )
        assertEquals(4, Day8.Grid(sampleData).getScenicScore(1,2));
        assertEquals(8, Day8.Grid(sampleData).getScenicScore(3,2))
    }

    @Test
    fun testPart2() {
        val data = Util().readData("day8-2.txt")
        assertEquals(672280, Day8().getPart2(data))
    }

}
