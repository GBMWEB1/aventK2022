import Day2.RoundResult.*
import Day2.Element.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun testDraw() {
        assertEquals(DRAW, Day2().battle(ROCK, ROCK))
        assertEquals(DRAW, Day2().battle(PAPER, PAPER))
        assertEquals(DRAW, Day2().battle(SCISSORS, SCISSORS))
    }

    @Test
    fun testYouWin() {
        assertEquals(WIN, Day2().battle(ROCK, PAPER))
        assertEquals(WIN, Day2().battle(PAPER, SCISSORS))
        assertEquals(WIN, Day2().battle(SCISSORS, ROCK))
    }

    @Test
    fun testYouLose() {
        assertEquals(LOSE, Day2().battle(ROCK, SCISSORS))
        assertEquals(LOSE, Day2().battle(PAPER, ROCK))
        assertEquals(LOSE, Day2().battle(SCISSORS, PAPER))
    }

    @Test
    fun testParseElement() {
        assertEquals(ROCK, Day2.Element.decode("A"))
        assertEquals(PAPER, Day2.Element.decode("B"))
        assertEquals(SCISSORS, Day2.Element.decode("C"))
        assertEquals(ROCK, Day2.Element.decode("X"))
        assertEquals(PAPER, Day2.Element.decode("Y"))
        assertEquals(SCISSORS, Day2.Element.decode("Z"))
    }

    @Test
    fun testFirstLine() {
        assertEquals(8, Day2().scoreRound("A Y"))
    }

    @Test
    fun testSecondLine() {
        assertEquals(1, Day2().scoreRound("B X"))
    }

    @Test
    fun testThirdLine() {
        assertEquals(6, Day2().scoreRound("C Z"))
    }

    @Test
    fun testScoreGame1Round() {
        val moves = listOf("A Y")
        assertEquals(8, Day2().scoreGame(moves))
    }

    @Test
    fun testScoreGame2Round() {
        val moves = listOf("A Y", "B X")
        assertEquals(9, Day2().scoreGame(moves))
    }

    @Test
    fun testScoreGame3Round() {
        val moves = listOf("A Y", "B X", "C Z")
        assertEquals(15, Day2().scoreGame(moves))
    }

    @Test
    fun testCh1() {
        val moves = Util().readData("day2-1.data")
        assertEquals(11841, Day2().scoreGame(moves))
    }

    @Test
    fun testDecodeResult() {
        assertEquals(LOSE, Day2.RoundResult.decode("X"))
        assertEquals(DRAW, Day2.RoundResult.decode("Y"))
        assertEquals(WIN, Day2.RoundResult.decode("Z"))
    }

    @Test
    fun testChoseMove1() {
        assertEquals(ROCK, Day2().chooseMove(ROCK, DRAW))
    }

    @Test
    fun testFirstLineStr2() {
        assertEquals(4, Day2().scoreTacticalRound("A Y"))
    }

    @Test
    fun testSecondLineStr2() {
        assertEquals(1, Day2().scoreTacticalRound("B X"))
    }

    @Test
    fun testThirdLineStr2() {
        assertEquals(7, Day2().scoreTacticalRound("C Z"))
    }

    @Test
    fun testScoreGame2() {
        val moves = listOf("A Y", "B X", "C Z")
        assertEquals(12, Day2().scoreTacticalGame(moves))
    }

    @Test
    fun testCh2() {
        val moves = Util().readData("day2-1.data")
        assertEquals(13022, Day2().scoreTacticalGame(moves))
    }

}