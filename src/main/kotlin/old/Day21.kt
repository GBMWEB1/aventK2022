package old

class Day21 {

    data class Monkey(var code: String,
                 var value : Long? = null,
                 var operation : String? = null
      ){
        private lateinit var operand: String
        private val operationCodes = mutableListOf<String>()
        private val operationValues = mutableMapOf<String, Long>()

        init{
            if (operation!= null){
                operationCodes.add(operation!!.substringBefore(" "))
                operationCodes.add(operation!!.substringAfterLast(" "))
                operand = operation!!.substringAfter(" ").substringBefore(" ")
            }
        }

        fun listen(code: String, value: Long){
            if (operationCodes.contains(code)){
                operationValues[code] = value
            }
        }

        fun canYell(): Boolean {
            return value!= null || operationValues.size==2
        }

        fun yell(): Long{
            if (value!= null){
                return value!!.toLong()
            }
            if (operationValues.size==2){
                val part1 = operationValues[operationCodes[0]]!!
                val part2 = operationValues[operationCodes[1]]!!
                return when (operand){
                    "*" -> part1 * part2
                    "/" -> part1 / part2
                    "+" -> part1 + part2
                    "-" -> part1 - part2
                    else-> throw Error("Unknown Operation")
                }
            }
            throw Error("Shouldn't Yell")
        }

        fun partsEqual(): GuessResult {
            val diff = operationValues[operationCodes[0]]!! - operationValues[operationCodes[1]]!!
            println("Comparing ${operationValues[operationCodes[0]]!!} and ${operationValues[operationCodes[1]]!!} - $diff ")

            if (diff> 0){
                return GuessResult.TO_HIGH
            } else if (diff < 0){
                return GuessResult.TO_LOW
            }
            return GuessResult.PUKKA
        }

        companion object{
            fun of(line : String): Monkey {
                val code = line.substringBefore(":")
                val operations = listOf('+','-','*','/')

                if (line.substringAfter(": ").toCharArray().any { operations.contains(it) }){
                    return Monkey(
                        code = code,
                        value =  null,
                        operation = line.substringAfter(": ")
                    )
                }
                return Monkey(
                    code = code,
                    value =  line.substringAfter(": ").toLong(),
                    operation = null)
            }
        }
    }

    fun getRoot(lines: List<String>): Long{
        val monkeys = lines.map { Monkey.of(it) }.toMutableList()

        while (true){
            val yellingMonkeys = monkeys.filter { it.canYell() }

            if (yellingMonkeys.any { it.code=="root"}){
                return yellingMonkeys.find { it.code=="root" }!!.yell()
            }
            yellingMonkeys.forEach { yellingMonkey ->
                val value = yellingMonkey.yell()
                monkeys.forEach { it.listen(yellingMonkey.code, value) }
                monkeys.remove(yellingMonkey)
            }
        }
    }

    enum class GuessResult{
        TO_LOW,
        TO_HIGH,
        PUKKA
    }

    private fun getHumanValue(readData: List<String>, humanValue: Long): GuessResult {
        val monkeys = readData.map { Monkey.of(it) }.toMutableList()
        // overide human value
        monkeys.find { it.code=="humn" }!!.value=humanValue
        while (true){
            val yellingMonkeys = monkeys.filter { it.canYell() }

            if (yellingMonkeys.any { it.code=="root"}){
                val rootOfEvil = yellingMonkeys.find { it.code=="root" }
                return rootOfEvil!!.partsEqual()
            }
            yellingMonkeys.forEach { yellingMonkey ->
                val value = yellingMonkey.yell()
                monkeys.forEach { it.listen(yellingMonkey.code, value) }
                monkeys.remove(yellingMonkey)
            }
        }
    }

    private fun nextGuess(lowerBound: Long, upperBound: Long): Long{
        return lowerBound + ((upperBound-lowerBound)/2)
    }

    fun findHumanValue(readData: List<String>): Long {
        var lowerBound = 0L
        var upperBound = Long.MAX_VALUE / 100000
        while (true){
            val valueToGuess = nextGuess(lowerBound, upperBound)
            println("Guessing $valueToGuess")
            val result = getHumanValue(readData, valueToGuess)

            if (result == GuessResult.TO_LOW){
                upperBound = valueToGuess
            }
            if (result == GuessResult.TO_HIGH){ // swapped over for part 2
                lowerBound = valueToGuess
            }
            if (result == GuessResult.PUKKA){
                return valueToGuess
            }
        }
    }
}
