package old

import kotlin.math.abs

class Day10 {

    class CPU (instructionsList: List<String>, var x: Int = 1, var cyclesExecuted: Int =0){

        private val crtChars = mutableListOf<Char>()
        private val instructions = processInstructions(instructionsList)

        private var signal = 0

        // expandTheListOfInstructions to include "noop" before all "addx". Moved up for readability
        private fun processInstructions(input: List<String> ): ArrayDeque<String>{

            val result = ArrayDeque<String>()

            input.forEach {
                if (it.startsWith("addx")){
                    result.add("noop")
                }
                result.add(it)
            }
            return result
        }

        fun cycle(){
            crtChars.add(getCrtChar())

            cyclesExecuted++
            signal = x * cyclesExecuted

            val instruction = popInstruction().split(" ")
            when (instruction[0]){
                "noop" -> {}
                "addx" -> { x += instruction[1].toInt() }
                else -> throw Exception("Unknown instruction $instruction")
            }
        }

        private fun getCrtChar(): Char{
            val pixelMiddle = cyclesExecuted % 40
            // sprint is 3 character wide
            if ( abs(pixelMiddle-x) <2){
                return '#'
            }
            return '.'
        }

        private fun popInstruction(): String {
            return instructions.removeFirst()
        }

        fun cycleUntilEnd(){
            while (instructions.isNotEmpty()){
                cycle()
            }
        }
        fun cycle(times: Int){
            repeat(times){
                cycle()
            }
        }

        fun signalStrength(): Int{
            return signal
        }

        fun getCrtRow(): String{
            return crtChars.joinToString("")
        }

        fun getCrtRows(): List<String> {
            return getCrtRow().chunked(40)
        }
    }
}