package old

class Day1 {

    private fun caloriesPerElf(calories: List<String>): List<Int> {
        val calsPerElf = mutableListOf<Int>()

        var elfTotal = 0
        for ((index, cal) in calories.withIndex()) {
            if (cal.isNotBlank()) {
                elfTotal += cal.toInt()
            } else {
                calsPerElf.add(elfTotal)
                elfTotal = 0
            }
            if (index == calories.lastIndex){
                calsPerElf.add(elfTotal)
            }
        }
        return calsPerElf
    }

    fun calculateMaxCalories(calories: List<String>): Int {
        return caloriesPerElf(calories).max()
    }

    fun calculateTop3Cals(calories: List<String>): Int {
        return caloriesPerElf(calories)
            .sortedDescending()
            .take(3)
            .sum()
    }
}