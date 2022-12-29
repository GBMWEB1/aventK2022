package old

import kotlin.math.abs

class Day23 {

    data class Elf(var x:Int, var y: Int){
        var propX: Int? = null
        var propY: Int? = null
    }

    enum class Direction{
        North,
        South,
        West,
        East
    }

    class Navigator(input: List<String>){
        private val elves = mutableListOf<Elf>()
        private val directions = ArrayDeque(listOf(Direction.North, Direction.South, Direction.West, Direction.East))

        init {
            for (y in input.indices){
                for (x in input[y].indices) {
                    if (input[y][x]=='#'){
                        elves.add(Elf(x,y))
                    }
                }
            }
        }

        private fun getDirection(): Direction {
            val dir = directions.removeFirst()
            directions.add(dir)
            return dir
        }

        private fun isElfNorth(currentElf : Elf): Boolean{
            return elves.any{ it.y == currentElf.y-1 && abs(it.x - currentElf.x)<2}
        }

        private fun isElfSouth(currentElf : Elf): Boolean{
            return elves.any{ it.y == currentElf.y+1 && abs(it.x - currentElf.x)<2}
        }

        private fun isElfWest(currentElf : Elf): Boolean{
            return elves.any{ it.x == currentElf.x-1 && abs(it.y - currentElf.y)<2}
        }

        private fun isElfEast(currentElf : Elf): Boolean{
            return elves.any{ it.x == currentElf.x+1 && abs(it.y - currentElf.y)<2}
        }

        private fun calculateProposedDirection(elf: Elf, startDirection: Direction){
            val directionsToTry = ArrayDeque(listOf(Direction.North, Direction.South, Direction.West, Direction.East))
            while(startDirection!= directionsToTry.first()){
                directionsToTry.add(directionsToTry.removeFirst())
            }
            for (direction in directionsToTry){
                if (direction== Direction.North && !isElfNorth(elf)){
                    elf.propY = elf.y-1
                    elf.propX = elf.x
                    return
                }
                if (direction== Direction.South && !isElfSouth(elf)){
                    elf.propY = elf.y+1
                    elf.propX = elf.x
                    return
                }
                if (direction== Direction.East && !isElfEast(elf)){
                    elf.propY = elf.y
                    elf.propX = elf.x+1
                    return
                }
                if (direction== Direction.West && !isElfWest(elf)){
                    elf.propY = elf.y
                    elf.propX = elf.x-1
                    return
                }
            }
        }

        fun displayElves(){
            val minX = elves.minOf { it.x }
            val maxX = elves.maxOf { it.x }
            val minY = elves.minOf { it.y }
            val maxY = elves.maxOf { it.y }
            println()
            for (y in minY..maxY){
                println()
                for (x in minX..maxX){
                    if (elves.any { it.x == x && it.y==y }){
                        print('#')
                    } else{
                        print('.')
                    }
                }
            }
        }

        fun countEmptyTiles(): Int{
            val minX = elves.minOf { it.x }
            val maxX = elves.maxOf { it.x }
            val minY = elves.minOf { it.y }
            val maxY = elves.maxOf { it.y }
            var count = 0
            for (y in minY..maxY){
                for (x in minX..maxX){
                    if (elves.none { it.x == x && it.y==y }) {
                        count++
                    }
                }
            }
            return count
        }

        fun move(rounds: Int = 10): Int {
            var elvesToMove = elves.filter { needsToMove(it) }

            var round = 0

            while(elvesToMove.isNotEmpty() && round<rounds){
                round++
                val startDirection = getDirection()
                elvesToMove.forEach { calculateProposedDirection(it, startDirection) }
                elvesToMove.forEach { moveIfPossible(it) }

                elvesToMove = elves.filter { needsToMove(it) }
            }
            return round+1
        }

        private fun needsToMove(elf: Elf): Boolean {
            return isElfEast(elf) || isElfWest(elf)|| isElfSouth(elf) || isElfNorth(elf)
        }

        private fun moveIfPossible(elf: Elf) {
            if (elf.propX!= null){
                if (elves.any { it!= elf &&  it.propX == elf.propX && it.propY == elf.propY }){
                    elves.filter { it.propX == elf.propX && it.propY == elf.propY }
                        .forEach {
                            it.propX=null
                            it.propY=null
                        }
                } else{
                    elf.x = elf.propX!!
                    elf.y = elf.propY!!
                }
            }
        }
    }
}


