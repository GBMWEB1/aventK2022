package old

import Util
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day7Test {

    @Test
    fun testS1part1() {
        val input = listOf(
            "$ cd /",
            "$ ls",
            "dir a",
            "14848514 b.txt",
            "8504156 c.dat",
            "$ cd a",
            "$ ls",
            "29116 f"
        )
        Day7().parseCommands(input).display(0)
    }


    @Test
    fun testPart1S() {
        val data = Util().readData("day7-1.txt")
        assertEquals(95437, Day7().getPart1(data))
    }

    @Test
    fun testPart1() {
        val data = Util().readData("day7-2.txt")
        assertEquals(1206825, Day7().getPart1(data))
    }

    @Test
    fun testPart2S() {
        val data = Util().readData("day7-1.txt")

        assertEquals(24933642, Day7().getPart2(data))
    }

    @Test
    fun testPart2() {
        val data = Util().readData("day7-2.txt")

        assertEquals(9608311, Day7().getPart2(data))
    }
}
