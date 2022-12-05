package old

class Day5 {

    class BuildQueue(input: List<String>) {

        val queues = build(input)

        private fun build(input: List<String>): List<ArrayDeque<Char>> {
            val queuesPos = input.indexOfFirst { line -> !line.contains("[") }

            val queues = input[queuesPos].substringAfterLast("  ").trim().toInt()
            val result : MutableList<ArrayDeque<Char>> = mutableListOf()
            for (colIndex in 1..queues){
                val stack = ArrayDeque<Char>()
                val col = 1 + ((colIndex-1) * 4)
                for (row in queuesPos-1 downTo 0) {
                    if (input[row].length> col && input[row][col].isLetter() ) {
                        stack.add(input[row][col])
                    }
                }
                result.add(stack)
            }
            return result
        }

        fun move(words: List<Int>){
            val num = words[0]
            val fromQueue = words[1]
            val toQueue = words[2]

            for (movement in 1..num){
                queues[toQueue-1].addLast(queues[fromQueue-1].removeLast())
            }
        }

        fun move2(words: List<Int>){
            val num = words[0]
            val fromQueue = words[1]
            val toQueue = words[2]
            val toMove = mutableListOf<Char>()
            for (movement in 1..num){
                toMove.add(queues[fromQueue-1].removeLast())
            }
            queues[toQueue-1].addAll(toMove.reversed())
        }

        fun getWord(): String{
            return queues.map { it.last() }.joinToString("")
        }
    }

    private fun parseInstructions(input: List<String>): List<List<Int>> {
        val queuesPos = input.indexOfFirst { line -> !line.contains("[") }
        val instructionsPos = queuesPos +2
        val instructions = input.subList(instructionsPos, input.size)
        return instructions.map { instruction -> instruction
            .split(" ")
            .filter { word -> !listOf("move", "from", "to").contains(word) }
            .map { it.toInt() }
        }
    }

    fun part1(input: List<String>): String {
        val queues = BuildQueue(input)
        val instructions = parseInstructions(input)
        instructions.forEach { queues.move(it) }
        return queues.getWord()
    }

    fun part2(input: List<String>): String {
        val queues = BuildQueue(input)
        val instructions = parseInstructions(input)
        instructions.forEach { queues.move2(it) }
        return queues.getWord()
    }
}