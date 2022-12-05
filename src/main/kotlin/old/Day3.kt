fun Char.asciiValue(): Int {
    if (this.isUpperCase()){
        return this.code - 38
    }
    return this.code-96
}

fun <T> List<T>.chunk(groupSize: Int): List<List<T>> {
    val tempList = mutableListOf<List<T>>()
    for (i in 0..this.size-groupSize step groupSize) {
        val group = mutableListOf<T>()
        for (j in 0 until groupSize){
            group.add(this[i+j])
        }
        tempList.add(group)
    }
    return tempList.toList()
}

class Day3 {

    fun splitRucksack(rucksack: String): List<String> {
        val chop = rucksack.length/2
        return listOf(rucksack.substring(0, chop),rucksack.substring(chop))
    }

    fun compareCompartments(comp1: String, comp2: String): Char {
        for (c in comp1) {
            if (comp2.contains(c)){
                return c
            }
        }
        throw IllegalArgumentException("Unable to find common char in $comp1 and $comp2")
    }

    fun getPriorityItemValue(rucksack: String): Int {
        val compartments = splitRucksack(rucksack)
        val commonItem = compareCompartments(compartments[0], compartments[1])
        return commonItem.asciiValue()
    }

    fun getTotalPriorities(rucksacks: List<String>): Int{
        return rucksacks.sumOf { rucksack -> getPriorityItemValue(rucksack) }
    }

    /////////
    // Ch2 //
    /////////
    fun getBadge(rucksacks: List<String>): Char{
        for (c in rucksacks[0]) {
            if (rucksacks[1].contains(c) && rucksacks[2].contains(c)){
                return c
            }
        }
        throw IllegalArgumentException("Unable to find common char in $rucksacks")
    }

    fun getBadgesValue(rucksacks: List<String>): Int {
        return rucksacks.chunk(3).sumOf { group -> getBadge(group).asciiValue() }
    }
}