class Day24 {

    data class ValleyDimensions(val maxX: Int, val maxY: Int, val startX : Int, val endX : Int)

    data class Blizzard(var x: Int, var y: Int, val direction : Char) {

        fun move( dimensions: ValleyDimensions) {
            when (direction) {
                '^' -> y -= 1
                'v' -> y += 1
                '<' -> x -= 1
                '>' -> x += 1
            }

            if (x==0){
                x = dimensions.maxX-1
            } else if (x == dimensions.maxX){
                x = 1
            }
            if (y==0){
                y = dimensions.maxY-1
            } else if (y == dimensions.maxY) {
                y = 1
            }
        }
    }

    data class State(val x: Int, val y: Int)


    class Valley(val input: List<String>){

        private var dimensions: ValleyDimensions
        private val blizzards = mutableListOf<Blizzard>()

        init{
            val startX = input.first().indexOf(".")
            val endX = input.last().indexOf(".")
            for (y in input.indices){
                for (x in input[y].indices){
                    val char = input[y][x]
                    if (!listOf('#','.').contains(char)){
                        blizzards.add(Blizzard(x,y,char))
                    }
                }
            }
            dimensions = ValleyDimensions(input.first().length-1, input.size-1, startX, endX)
        }

        private fun getDisplayStartEnd(pos: Int): String{
            val chars = mutableListOf<Char>()
            for (x in 0 .. dimensions.maxX){
                if (x==pos){
                    chars.add('.')
                } else{
                    chars.add('#')
                }
            }
            return chars.joinToString("")
        }

        private fun getValleyRow(y: Int): String{
            val chars = mutableListOf<Char>()
            chars.add('#')
            for (x in 1 until dimensions.maxX){
                val blizzardsAtPoint = blizzards.filter { it.x == x && it.y == y }
                when (blizzardsAtPoint.size){
                    0 -> chars.add('.')
                    1 -> chars.add(blizzardsAtPoint[0].direction)
                    else -> chars.add(blizzardsAtPoint.size.digitToChar())
                }
            }
            chars.add('#')
            return chars.joinToString("")
        }

        fun display(){
            println()
            println(getDisplayStartEnd(dimensions.startX))
            // for each row, column, count blizzards
            for (y in 1 until dimensions.maxY){
                println(getValleyRow(y))
            }
            println(getDisplayStartEnd(dimensions.endX))
        }

        fun moveBlizzards() {
            blizzards.forEach { it.move(dimensions) }
        }

        fun navigateToEnd() : Int{
            return navigateToLocation(
                State(dimensions.startX, 0),
                State(dimensions.endX, dimensions.maxY)
            )
        }

        fun navigateToStart() : Int{
            return navigateToLocation(
                State(dimensions.endX, dimensions.maxY),
                State(dimensions.startX, 0),
            )
        }

        private fun navigateToLocation(startState: State, endState : State) : Int{
            var states = setOf(startState)
            var minutes = 0
            while (true){
                minutes++
                moveBlizzards()
                states = states.flatMap { navigate(it) }.toSet()
                if (states.any { it ==endState}){
                    return minutes
                }
            }
        }

        private fun noBlizzards(x: Int, y: Int): Boolean{
            return blizzards.none { it.x == x && it.y == y }
        }

        private fun navigate(state: State) : List<State> {
            val nextStates = mutableListOf<State>()

            if (state.y>0 && state.y<dimensions.maxY){
                //right
                if (state.x+1< dimensions.maxX && noBlizzards(state.x+1, state.y)){
                    nextStates.add(State(state.x+1, state.y))
                }
                // left
                if (state.x-1> 0 && noBlizzards(state.x-1, state.y)){
                    nextStates.add(State(state.x-1, state.y))
                }
            }

            //down
            if (state.y+1 == dimensions.maxY && state.x == dimensions.endX ){
                nextStates.add(State(state.x, state.y+1))
            }
            else if (state.y+1< dimensions.maxY && noBlizzards(state.x, state.y+1)){
                nextStates.add(State(state.x, state.y+1))
            }
            // up
            if (state.y-1 == 0 && state.x == dimensions.startX ){
                nextStates.add(State(state.x, 0))
            }
            else if (state.y-1> 0 && noBlizzards(state.x, state.y-1)){
                nextStates.add(State(state.x, state.y-1))
            }
            // stay the same position
            if (noBlizzards(state.x, state.y)){
                nextStates.add(state)
            }
            return nextStates
        }

    }
}


