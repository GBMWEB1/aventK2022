package old

import Util
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class Day17Test {

    @Test
    fun rockFall() {

        val cave = Day17.LargeCave()
        assertEquals(
            listOf(
                "|.......|",
                "|.......|",
                "|.......|",
                "|.......|",
                "+-------+"
            ),
            cave.getDisplay()
        )

        cave.addRock(Day17.Rock.ONE)
        assertEquals(
            listOf(
                "|..@@@@.|",
                "|.......|",
                "|.......|",
                "|.......|",
                "+-------+"
            ),
            cave.getDisplay()
        )

        cave.pushFallingRockRight()
        assertEquals(
            listOf(
                "|...@@@@|",
                "|.......|",
                "|.......|",
                "|.......|",
                "+-------+"
            ),
            cave.getDisplay()
        )

        cave.rockFall()
        assertEquals(
            listOf(
                "|...@@@@|",
                "|.......|",
                "|.......|",
                "+-------+"
            ),
            cave.getDisplay()
        )

        // nothing happens
        cave.pushFallingRockRight()
        assertEquals(
            listOf(
                "|...@@@@|",
                "|.......|",
                "|.......|",
                "+-------+"
            ),
            cave.getDisplay()
        )
    }

    @Test
    fun firstRockFall1ToEnd() {
        val largeCave = Day17.LargeCave()
        largeCave.addRock(Day17.Rock.ONE)

        largeCave.pushFallingRock()//right
        largeCave.rockFall()

        largeCave.pushFallingRock() //right
        largeCave.rockFall()

        largeCave.pushFallingRock() //right
        largeCave.rockFall()

        assertEquals(
            listOf(
                "|...@@@@|",
                "+-------+"
            ),
            largeCave.getDisplay()
        )

        largeCave.pushFallingRock() //left
        assertEquals(
            listOf(
                "|..@@@@.|",
                "+-------+"
            ),
            largeCave.getDisplay()
        )

        largeCave.rockFall()
        assertEquals(
            listOf(
                "|..####.|",
                "+-------+"
            ),
            largeCave.getDisplay()
        )

        // add Rock 2
        largeCave.addRock(Day17.Rock.TWO)

        assertEquals(
            listOf(
                "|...@...|",
                "|..@@@..|",
                "|...@...|",
                "|.......|",
                "|.......|",
                "|.......|",
                "|..####.|",
                "+-------+"
            ),
            largeCave.getDisplay()
        )

        largeCave.pushFallingRock() //left
        assertEquals(
            listOf(
                "|..@....|",
                "|.@@@...|",
                "|..@....|",
                "|.......|",
                "|.......|",
                "|.......|",
                "|..####.|",
                "+-------+"
            ), largeCave.getDisplay()
        )

        largeCave.rockFall()
        assertEquals(
            listOf(
                "|..@....|",
                "|.@@@...|",
                "|..@....|",
                "|.......|",
                "|.......|",
                "|..####.|",
                "+-------+"
            ), largeCave.getDisplay()
        )

        largeCave.pushFallingRock() //right
        assertEquals(
            listOf(
                "|...@...|",
                "|..@@@..|",
                "|...@...|",
                "|.......|",
                "|.......|",
                "|..####.|",
                "+-------+"
            ), largeCave.getDisplay()
        )

        largeCave.rockFall()
        assertEquals(
            listOf(
                "|...@...|",
                "|..@@@..|",
                "|...@...|",
                "|.......|",
                "|..####.|",
                "+-------+"
            ), largeCave.getDisplay()
        )

        largeCave.pushFallingRock() //left
        assertEquals(
            listOf(
                "|..@....|",
                "|.@@@...|",
                "|..@....|",
                "|.......|",
                "|..####.|",
                "+-------+"
            ), largeCave.getDisplay()
        )

        largeCave.rockFall()
        assertEquals(
            listOf(
                "|..@....|",
                "|.@@@...|",
                "|..@....|",
                "|..####.|",
                "+-------+"
            ), largeCave.getDisplay()
        )

        largeCave.pushFallingRock() //right
        assertEquals(
            listOf(
                "|...@...|",
                "|..@@@..|",
                "|...@...|",
                "|..####.|",
                "+-------+"
            ), largeCave.getDisplay()
        )

        largeCave.rockFall()
        assertEquals(
            listOf(
                "|...#...|",
                "|..###..|",
                "|...#...|",
                "|..####.|",
                "+-------+"
            ), largeCave.getDisplay()
        )

    }

    @Test
    fun dropRocks() {
        val largeCave = Day17.LargeCave()
        largeCave.dropRock()
        assertEquals(
            listOf(
                "|..####.|",
                "+-------+"
            ),
            largeCave.getDisplay().filter { it!= "|.......|" }
        )
        largeCave.dropRock()
        assertEquals(
            listOf(
                "|...#...|",
                "|..###..|",
                "|...#...|",
                "|..####.|",
                "+-------+"
            ), largeCave.getDisplay()
        )

        largeCave.dropRock()
        assertEquals(
            listOf(
            "|..#....|",
            "|..#....|",
            "|####...|",
            "|..###..|",
            "|...#...|",
            "|..####.|",
            "+-------+"
            ), largeCave.getDisplay()
        )

        largeCave.dropRock()
        assertEquals(
            listOf(
                "|....#..|",
                "|..#.#..|",
                "|..#.#..|",
                "|#####..|",
                "|..###..|",
                "|...#...|",
                "|..####.|",
                "+-------+"
            ), largeCave.getDisplay()
        )

        largeCave.dropRock()
        assertEquals(
            listOf(
                "|....##.|",
                "|....##.|",
                "|....#..|",
                "|..#.#..|",
                "|..#.#..|",
                "|#####..|",
                "|..###..|",
                "|...#...|",
                "|..####.|",
                "+-------+"
            ), largeCave.getDisplay()
        )
    }



    // Part 2 - Find Pattern

    @Test
    fun findPartDetermineRepSample(){
        val largeCave = Day17.LargeCave()
        val rep = largeCave.determineRepetition()
        assertEquals(35,rep)
    }

    @Test
    fun findHeight2022Sample(){
        val largeCave = Day17.LargeCave()
        assertEquals(3068, largeCave.calculateHeight(35,2022))
    }

    @Test
    fun findHeightPart2Sample(){
        val largeCave = Day17.LargeCave()
        assertEquals(1514285714288, largeCave.calculateHeight(35,1000000000000))
    }

    @Test
    fun findPartDetermineRep2(){
        val largeCave = Day17.LargeCave(Util().readData("day17.txt")[0])
        val rep = largeCave.determineRepetition()
        assertEquals(1725,rep)
    }

    @Test
    fun findPart2(){
        val largeCave = Day17.LargeCave(Util().readData("day17.txt")[0])
        assertEquals(1561739130391, largeCave.calculateHeight(1725,1000000000000))
     }
}
