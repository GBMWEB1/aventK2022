import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day23Test{

    @Test
    fun initialise(){
        val smallerExample = listOf(
            ".....",
            "..##.",
            "..#..",
            ".....",
            "..##.",
            ".....",
        )
        val navigator = Day23.Navigator(smallerExample)
        navigator.move()
        navigator.displayElves()
    }

    @Test
    fun initialiseLargeExample(){
        val example = listOf(
        ".......#......",
        ".....###.#....",
        "...#...#.#....",
        "....#...##....",
        "...#.###......",
        "...##.#.##....",
        "....#..#......",
        )
        val navigator = Day23.Navigator(example)
        navigator.move()
        navigator.displayElves()
        assertEquals(110, navigator.countEmptyTiles())
    }


    @Test
    fun part1(){
        val navigator = Day23.Navigator(Util().readData("day23.txt"))
        navigator.move(10)
        navigator.displayElves()
        assertEquals(3780, navigator.countEmptyTiles())
    }


    @Test
    fun part2Sample(){
        val example = listOf(
            ".......#......",
            ".....###.#....",
            "...#...#.#....",
            "....#...##....",
            "...#.###......",
            "...##.#.##....",
            "....#..#......",
        )
        val navigator = Day23.Navigator(example)
        assertEquals(20, navigator.move(2000))
    }

    @Test
    fun part2(){
        val navigator = Day23.Navigator(Util().readData("day23.txt"))
        assertEquals(930, navigator.move(2000))
    }
}