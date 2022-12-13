package old

class Day13(private val debug : Boolean = false) {
    data class Packet(var value: String){
        fun parse(): List<Packet>{
            val result = mutableListOf<Packet>()
            val stringToSplit = (value.substring(1).substring(0,value.length-2))

            if (stringToSplit.isEmpty()){
                return listOf()
            }
            val charactersSoFar = mutableListOf<Char>()
            var innerCount = 0
            for (character in stringToSplit){
                if (character == '['){
                    innerCount++
                }
                if (character == ']'){
                    innerCount--
                }
                if (innerCount==0 && character== ','){
                    result.add(Packet(charactersSoFar.joinToString("")))
                    charactersSoFar.clear()
                } else{
                    charactersSoFar.add(character)
                }
            }
            result.add(Packet(charactersSoFar.joinToString("")))
            return result
        }

        fun isArray(): Boolean{
            return value.startsWith("[")
        }

        fun convertToArray(): Packet {
            return Packet("[$value]")
        }
    }

    enum class Answer(val returnValue: Int){
        UNKNOWN(0),
        RIGHT_ORDER(-1),
        WRONG_ORDER(1)
    }

    fun isInRightOrder(left: String, right: String): Boolean{
        return Answer.RIGHT_ORDER == compare(Packet(left), Packet(right))
    }

    fun isInRightOrderCompare(left: String, right: String):Int{
        return compare(Packet(left), Packet(right)).returnValue
    }

    fun compare(left: Packet, right: Packet, level: Int=0) : Answer {
        printPadded(level, "Comparing $left vs $right")

        val rightParts = right.parse()
        left.parse().forEachIndexed { index, leftPart ->
            if (index == rightParts.size){
                printPadded(level+1,"Right side ran out of items, so inputs are in the right order")
                return Answer.WRONG_ORDER
            }

            val rightPart = rightParts[index]
            printPadded(level+1,"Comparing $leftPart vs $rightPart")

            var comparison = Answer.UNKNOWN
            if (leftPart.isArray() && rightPart.isArray()){
                comparison = compare(leftPart, rightPart, level+1)
            }
            else if (leftPart.isArray()){
                printPadded(level+1,"Mixed types; convert right to [$rightPart] and retry comparison")
                comparison = compare(leftPart, rightPart.convertToArray(),level+1)
            }
            else if (rightPart.isArray()){
                printPadded(level+1,"Mixed types; convert left to [$leftPart] and retry comparison")
                comparison = compare(leftPart.convertToArray(), rightPart, level+1)
            } else{
                if (leftPart.value.toInt()> rightPart.value.toInt()){
                    printPadded(level+2,"Right side is smaller, so inputs are not in the right order")
                    return Answer.WRONG_ORDER
                }
                if (leftPart.value.toInt()< rightPart.value.toInt()){
                    printPadded(level+2,"Left side is smaller, so inputs are in the right order")
                    return Answer.RIGHT_ORDER
                }
            }
            if (comparison!= Answer.UNKNOWN){
                return comparison
            }
        }
        if (left.parse().size < rightParts.size){
            printPadded(level+1,"Left side ran out of items, so inputs are in the right order")
            return Answer.RIGHT_ORDER
        }

        if (level==0){
            return Answer.RIGHT_ORDER
        }
        return Answer.UNKNOWN
    }

    private fun printPadded(level: Int, content: String) {
        if (debug){
            val pad = Array(level*2){' '}.joinToString("")
            println("$pad - $content")
        }
    }
}