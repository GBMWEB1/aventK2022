import Day2.Element.*
import Day2.RoundResult.*

class Day2 {

    enum class Element(val score: Int ){
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        companion object {
            fun decode (code: String) =
                when (code) {
                    "A","X" -> ROCK
                    "B","Y" -> PAPER
                    "C", "Z" -> SCISSORS
                    else -> throw Exception("Unknown $code")
                }
        }
    }

    enum class RoundResult {
        LOSE,
        WIN,
        DRAW;

        companion object {
            fun decode (code: String) =
                when (code){
                    "X" -> LOSE
                    "Y" -> DRAW
                    "Z" -> WIN
                    else -> {
                        throw IllegalArgumentException("Unknown $code")
                    }
                }
        }
    }

    fun scoreGame(moves: List<String>): Int {
        return moves.sumOf { move -> scoreRound(move) }
    }

    fun scoreRound(move: String) : Int {
        val parts = move.split(" ")
        val opponentMove = Element.decode(parts[0])
        val yourMove = Element.decode(parts[1])
        val result = battle(opponentMove,yourMove)
        return score(result,yourMove)
    }

    fun battle(opponent: Element,you: Element): RoundResult {
        return when (opponent to you){
            ROCK to PAPER, PAPER to SCISSORS, SCISSORS to ROCK -> WIN
            PAPER to ROCK, SCISSORS to PAPER, ROCK to SCISSORS -> LOSE
            else -> DRAW
        }
    }

    private fun score(result: RoundResult, yourMove: Element) : Int {
        return when (result){
            WIN -> yourMove.score + 6
            DRAW -> yourMove.score + 3
            LOSE -> yourMove.score
        }
    }

    /////////////////
    // Challenge 2 //
    /////////////////

    fun scoreTacticalGame(moves: List<String>): Int {
        return moves.sumOf { move -> scoreTacticalRound(move) }
    }

    fun scoreTacticalRound(move: String) : Int {
        val parts = move.split(" ")
        val opponentMove = Element.decode(parts[0])
        val expectedResult = RoundResult.decode(parts[1])
        val yourMove = chooseMove(opponentMove,expectedResult )
        val result = battle(opponentMove,yourMove)
        return score(result, yourMove)
    }

    fun chooseMove(opponent:Element,result: RoundResult) : Element {
        return when(opponent){
            ROCK ->
                when (result){
                    DRAW -> ROCK
                    WIN -> PAPER
                    LOSE -> SCISSORS
                }
            PAPER ->
                when (result){
                    DRAW -> PAPER
                    WIN -> SCISSORS
                    LOSE -> ROCK
                }
            SCISSORS ->
                when (result){
                    DRAW -> SCISSORS
                    WIN -> ROCK
                    LOSE -> PAPER
            }
        }
    }
}