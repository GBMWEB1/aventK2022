class Day4 {

    fun String.expandIntSet(): Set<Int> {
        val parts = this.split("-")
        return  (parts[0].toInt() .. parts[1].toInt()).toSet()
    }

    fun countOverlaps(sections: List<String>): Int{
        return sections.count { overlap(it)}
    }

    fun overlap(sectionRanges: String): Boolean {
        val sections = sectionRanges.split(",")
        return match( sections[0].expandIntSet(),sections[1].expandIntSet())
    }

    fun match(number1: Set<Int>, numbers2: Set<Int>): Boolean {
      return (number1.containsAll(numbers2) || numbers2.containsAll(number1))
    }

    // Ch 2
    fun countAnyOverlaps(duties: List<String>): Int {
        return duties.count {
            val sections = it.split(",")
            anyMatch(sections[0].expandIntSet(), sections[1].expandIntSet())
        }
    }

    fun anyMatch(number1: Set<Int>, numbers2: Set<Int>): Boolean {
        return number1.intersect(numbers2).isNotEmpty()
    }
}