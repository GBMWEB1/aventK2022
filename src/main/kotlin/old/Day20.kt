package old

import kotlin.math.abs

class Day20 {
    data class SpecialNumber(
        val value: Int,
        val origPosition : Int) {

        companion object {
            fun fromList(line: List<Int>): List<SpecialNumber>{
                return line.mapIndexed { index, value -> SpecialNumber(value, index)   }
            }
        }
    }

    class MixingFile(numbers: List<Int>){
        val values = SpecialNumber.fromList(numbers).toMutableList();

        var idx = 0;
        fun process() : List<Int>{
            val specialNumber = values.find { it.origPosition == idx }

            val currentPos = values.indexOf(specialNumber)

            var nextPos = currentPos

            if (specialNumber!!.value>0){
                // moving forward.
                nextPos = currentPos + specialNumber.value.mod(values.size)

                if (nextPos>6){
                    nextPos = nextPos - (values.size-1)
                }

            } else if (specialNumber!!.value<0) {
                nextPos = currentPos - (abs(specialNumber.value).mod(values.size))
                if (nextPos <0){
                    nextPos += (values.size-1)
                } else if (nextPos==0){
                    nextPos = values.size-1
                }
            }

            if (nextPos > currentPos){
                values.remove(specialNumber)
                values.add(nextPos,specialNumber)
            } else if (nextPos < currentPos){
                values.remove(specialNumber)
                values.add(nextPos,specialNumber)
            }

            idx++
            return getNumbers()
        }

        fun getNumbers(): List<Int> {
            return values.map { it.value }
        }
    }

    // store in the list, but - record the original index
    // To move forward
    // get the index in the list at the moment.
    // to move forward, calcualte new position
         // (current position + val % size)
         // (current position - val) % size) . if negative, add to the size.
         // remove from old add to new.

}
