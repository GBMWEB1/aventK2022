package old

class Day9 {

    data class Pos (var x: Int, var y : Int)

    class Rope ( private val noOfKnots: Int = 2, private val debug: Boolean = false) {

        private var knots = mutableListOf<Pos>()

        val positions = mutableSetOf<Pos>()

        fun init(initialKnots: List<Pos> = listOf(Pos(0, 0),Pos(0,0))
        ){
            knots.add(initialKnots[0].copy())
            repeat(noOfKnots-1){
                knots.add(initialKnots.last().copy())
            }
            positions.add(getTailPosition().copy())
        }
        fun getTailPosition(): Pos {
            return knots.last()
        }

        fun getHeadPosition(): Pos {
            return knots.first()
        }

        fun move(command: String) {
            val dir = command.split(" ")[0]
            val size = command.split(" ")[1].toInt()
            when (dir){
                "R" -> {
                    repeat(size) {
                        moveRight()
                        positions.add(getTailPosition().copy())
                    }
                }
                "U" -> {
                    repeat(size) {
                        moveUp()
                        if (debug) {
                            displayState()
                        }
                        positions.add(getTailPosition().copy())
                    }
                }
                "L" -> {
                    repeat(size) {
                        moveLeft()
                        if (debug) {
                            displayState()
                        }
                        positions.add(getTailPosition().copy())
                    }
                }
                "D" -> {
                    repeat(size) {
                        moveDown()
                        positions.add(getTailPosition().copy())
                    }
                }

                else -> throw Exception("Unknown Direction $dir")
            }
            if (debug) {
                displayState()
            }

        }

        private fun tug(pos1: Pos, pos2: Pos) : Pos {

            val xDif = pos2.x - pos1.x
            val yDif = pos2.y - pos1.y

            // 8 movements
            // up-left, up-right,  left, right, up, down, down-left, down-right
            // tug left
            var y = pos1.y
            // right
            if ( xDif> 1){
                // up
                if (yDif> 1){
                    y++ //down
                } else if (yDif<  -1){
                    y--
                } else{
                    y = pos2.y
                }
                return Pos(
                    pos1.x+1,
                    y
                )
            }
            // tug right
            else if (xDif < -1){
                if (yDif> 1){
                    y++
                } else if (yDif<  -1){
                    y--
                } else{
                    y = pos2.y
                }
                return Pos(
                    pos1.x - 1,
                    y
                )
            }
            // tug up
            else if (yDif > 1){
                return Pos(
                    pos2.x,
                pos1.y +1,
                )
            }
            // tug down
            else if (yDif < -1){
                return Pos(
                    pos2.x,
                    pos1.y -1
                )
            }
            return pos1
        }

        private fun moveKnots(){
            for (knot in 1 until knots.size){
                val afterPos = tug(knots[knot], knots[knot-1])
                knots[knot].x = afterPos.x
                knots[knot].y = afterPos.y
            }
        }

        private fun moveRight(){
            knots[0].x++
            moveKnots()
        }

        private fun moveUp() {
            knots[0].y++
            moveKnots()

        }

        private fun moveLeft() {
            knots[0].x--
            moveKnots()
        }

        private fun moveDown() {
            knots[0].y--
            moveKnots()
        }

        fun displayState(){
            val maxRows = maxOf(positions.maxOf { it.y },knots.maxOf { it.y })
            val maxCols = maxOf(positions.maxOf { it.x },knots.maxOf { it.x })
            for (row in maxRows downTo 0){
                println()
                for (col in 0..maxCols){
                    val cell = Pos(col, row)
                    if (cell == knots[0]){
                        print("H")
                    } else if (knots.contains(cell)){
                        print (knots.indexOfFirst { cell==it}+1)
                    }else if (positions.first() == cell){
                        print("s")
                    } else{
                        print(".")
                    }
                }
            }
            println()
            println("---")
        }
    }
}