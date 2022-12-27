class Day25 {

    fun toDecimal(input: String): Long {
        var power = 1L
        var result =0L
        for (character in input.reversed()){
            val characterValue = when (character) {
                '=' -> -2
                '-' -> -1
                else -> character.digitToInt()
            }
            result += characterValue * power
            power *= 5
        }
        return result
    }

    fun sumDecimal(input: List<String>): Long {
        return input.sumOf { toDecimal(it) }
    }

    fun toSpecial(input: Long): String {
        val chars = mutableListOf<Char>()
        var carryOver =0
        for (character in input.toString(5).reversed()) {
            when (val characterValue = carryOver + character.digitToInt()) {
                5 -> {
                    chars.add('0')
                    carryOver = 1
                }
                4 -> {
                    chars.add('-')
                    carryOver = 1
                }
                3 -> {
                    chars.add('=')
                    carryOver = 1
                }
                else -> {
                    chars.add(characterValue.digitToChar())
                    carryOver =0
                }
            }
        }
        if (carryOver>0){
            chars.add(carryOver.digitToChar())
        }
        return chars.joinToString("").reversed()
    }
}
