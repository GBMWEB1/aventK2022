

class Day10 {

    class CPU (instructionsList: List<String>){
        var x = 1
        var cyclesExecuted =0;
        val instructions = ArrayDeque(instructionsList)
        var processing = false;
        var nextValue = 0;
        fun popInstruction(): String {
            return instructions.removeFirst();
        }

        fun signalStrength(): Int{
            return x * cyclesExecuted;
        }
        fun cycle(times: Int){
            repeat(times){
                cycle();
            }
        }
        fun cycle(){
            if (processing) {
                x+= nextValue
                nextValue = 0;
                processing = false;

            } else {
                val instruction = popInstruction().split(" ");
                when (instruction[0]){
                    "noop" -> noop();
                    "addx" -> addx(instruction[1].toInt())
                    else -> throw Exception("unknown instruction $instruction")
                }
            }
            cyclesExecuted++;
        }
        fun noop(){
        }

        fun addx(value: Int) {
            processing = true;
            nextValue = value;
        }
    }
}