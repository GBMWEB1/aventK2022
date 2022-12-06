package old

data class Move(val quantity: Int, val fromQueue: Int, val toQueue: Int){
    companion object {
        fun of (line:String): Move{
            return line.split(" ")
                .filter { word -> !listOf("move", "from", "to").contains(word) }
                .map { it.toInt() }
                .let { Move(it[0], it[1], it[2]) }
        }
    }
}
class Day5 {

    class BuildQueue(input: List<String>) {

        val queues = build(input)

        private fun build(input: List<String>): List<ArrayDeque<Char>> {
            val queuesPos = input.indexOfFirst { line -> !line.contains("[") }

            val noOfStacks = input[queuesPos].substringAfterLast("  ").trim().toInt()
            val stacks = List(noOfStacks) { ArrayDeque<Char>()}
            for (stackIndex in 0 until noOfStacks){
                val col = 1 + (stackIndex * 4)
                for (row in queuesPos-1 downTo 0) {
                    if (input[row][col].isLetter() ) {
                        stacks[stackIndex].add(input[row][col])
                    }
                }
            }
            return stacks
        }

        fun move(move: Move){
            repeat(move.quantity){
                queues[move.toQueue-1].addLast(queues[move.fromQueue-1].removeLast())
            }
        }

        fun move2(move: Move){
            val toMove = mutableListOf<Char>()
            repeat(move.quantity){
                toMove.add(queues[move.fromQueue-1].removeLast())
            }
            queues[move.toQueue-1].addAll(toMove.reversed())
        }

        fun getWord(): String{
            return queues.map { it.last() }.joinToString("")
        }
    }

    private fun parseMoves(input: List<String>): List<Move> {
        return input
            .filter { it.contains("move") }
            .map { Move.of(it) }
    }

    fun part1(input: List<String>): String {
        val queues = BuildQueue(input)
        parseMoves(input).forEach { queues.move(it) }
        return queues.getWord()
    }

    fun part2(input: List<String>): String {
        val queues = BuildQueue(input)
        parseMoves(input).forEach { queues.move2(it) }
        return queues.getWord()
    }
}