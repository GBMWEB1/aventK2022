package old

import kotlin.math.abs

class Day20 {
    data class SpecialNumber(
        val value: Long,
        val origPosition : Int) {

        companion object {
            fun fromList(line: List<Int>, decryptionKey: Long): List<SpecialNumber>{
                return line.mapIndexed { index, value -> SpecialNumber(value.toLong()* decryptionKey, index)   }
            }
        }
    }

    class MixingFile(numbers: List<Int>, decryptionKey: Long = 1){
        private val values = SpecialNumber.fromList(numbers, decryptionKey).toMutableList()

        private var idx = 0
        fun process() : List<Long>{
            val specialNumber = values.find { it.origPosition == idx }

            val currentPos = values.indexOf(specialNumber)

            var nextPos = currentPos

            if (specialNumber!!.value>0){
                nextPos += specialNumber.value.mod(values.size - 1)
            } else if (specialNumber.value<0) {
                nextPos -= (abs(specialNumber.value).mod(values.size - 1))
            }

            if (nextPos<0){
                nextPos += (values.size - 1)
            }
            else if (nextPos>values.size-1){
                nextPos -= (values.size - 1)
            }

            values.remove(specialNumber)
            values.add(nextPos,specialNumber)

            idx++
            return getNumbers()
        }

        fun getNumbers(): List<Long> {
            return values.map { it.value }
        }

        fun processAll(): List<Long> {
            idx = 0
            repeat (values.size){
                process()
            }
            return getNumbers()
        }

        fun getNumberAtPosition(pos: Int): Long {
            val firstPosition = values.indexOfFirst { it.value == 0L }

            var nextPos = firstPosition + pos
            if (nextPos>=values.size){
                nextPos = nextPos.mod(values.size)
            }
            return values[nextPos].value
        }

        fun getGroveCoordinates(): Long {
            return getNumberAtPosition(1000)+
            getNumberAtPosition(2000)+
            getNumberAtPosition(3000)
        }
    }
}
