package old

class Day22 {
    class JungleMap(val map: List<MutableList<Char>>) {

        fun getCubeSize(): Int{
            return map.size/3
        }

        fun getCubeFace(x: Int, y:Int): Int{
            val cubeSize = getCubeSize()

            if (y<cubeSize){
                return 1
            }

            if (y>=cubeSize && y< (cubeSize+cubeSize) ){
                if (x<cubeSize){
                    return 2
                }
                if (x< cubeSize+cubeSize){
                    return 3
                }
                return 4
            }
            if (x < cubeSize*3){
                return 5
            }
            return 6
        }

        fun isWall(x: Int, y:Int): Boolean {
           val character = map[y][x]
           if (character == '#'){
                return true
           }
            return false
        }

        fun plotDirection(pos : Position) {
            map[pos.y][pos.x] = pos.direction
        }

        fun offGrid(x: Int, y: Int): Boolean {
            if (y >= map.size){
                return true
            }
            if (y<0){
                return true
            }
            if (x<0){
                return true
            }
            if (x>= map[y].size){
                return true
            }
            if (map[y][x]==' '){
                return true
            }
            return false
        }

        fun getStartY(x: Int): Int {
            map.forEachIndexed { index, list ->
                val c = list[x]
                if (c != ' ') {
                    return index
                }
            }
            return -1
        }

        fun getStartX(nextY: Int = 0): Int {
            map[nextY].forEachIndexed { index, c ->
                if (c != ' ') {
                    return index
                }
            }
            return -1
        }

        fun getLastX(y: Int): Int {
            for (x in map[y].size-1 downTo 0){
                val c = map[y][x]
                if (c != ' ') {
                    return x
                }
            }
            return -1
        }

        fun getLastY(x: Int): Int {
            map.reversed().forEachIndexed { index, list ->
                if (x<list.size) {
                    val c = list[x]
                    if (c != ' ') {
                        return map.size - 1 - index
                    }
                }
            }
            return -1
        }

        companion object {
            fun fromList(list: List<String>): JungleMap {
                return JungleMap(list.map { it.toCharArray().toMutableList() })
            }
        }
    }


    class Instructions(ins : String){

        val list = ArrayDeque<String>()

        init{
            // parse through array, and add to list of instructions
            var soFar = ""
            for (character in ins){
                if (listOf('L','R').contains(character)){
                    if (soFar.isNotEmpty()){
                        list.add(soFar)
                    }
                    list.add(character.toString())
                    soFar=""
                } else{
                    soFar += character
                }
            }
            if (soFar.isNotEmpty()){
                list.add(soFar)
            }
        }
    }

    data class Position(var x : Int, var y: Int, var direction: Char)

    class Navigator(val jungleMap: JungleMap,
                    private val instructions: Instructions,
                    private val debug: Boolean) {


        var position = Position(jungleMap.getStartX(), 0, '>')

        // part 2, identify that we are navigating a cube
        var cube: Boolean = false

        companion object{
            fun fromList(list: List<String>, debug: Boolean = false): Navigator {
                val jungleMapList = list.subList(0, list.indexOf(""))
                return Navigator(
                    JungleMap.fromList(jungleMapList),
                    Instructions( list[list.indexOf("")+1]),
                    debug)
            }
        }

        fun displayMap(){
            jungleMap.map.forEach { println(it.joinToString("")) }
            println("")
        }

        private fun teleportMethodOne(nextPosition: Position) : Position {

            var newX = nextPosition.x
            var newY = nextPosition.y
            val newDirection = nextPosition.direction

            when (newDirection) {
                '>' -> newX = jungleMap.getStartX(newY)
                '<' -> newX = jungleMap.getLastX(newY)
                'v' -> newY = jungleMap.getStartY(newX)
                '^' -> newY = jungleMap.getLastY(newX)
            }
            return Position(newX, newY, newDirection)
        }

        //    'Left' -> Top of 3 - Facing Down
        //    'Above -> Top of 2 - Facing Down
        //    'Right -> Right of 6, Facing Left
        fun teleport1(pos: Position): Position {
            val cubeSize = jungleMap.getCubeSize()
            return when (pos.direction) {
                '<' -> Position(cubeSize + pos.y, cubeSize, 'v')
                '^' -> Position(cubeSize -1 - (pos.x - cubeSize*2), cubeSize, 'v')
                '>' -> Position(cubeSize*4-1, cubeSize * 3 -1 - pos.y, '<')
                else -> throw Error("Invalid teleport from face 1 when moving ${pos.direction}")
            }
        }

        // 2
        //    'Left' -> Bottom of 6, facing up
        //    'Above -> Top of 1 - Facing Down
        //    'Below' -> Bottom of 5 - Facing Up
        fun teleport2(pos: Position): Position {
            val cubeSize = jungleMap.getCubeSize()
            return when (pos.direction) {
                '<' -> Position(cubeSize*4 -1 - pos.y.mod(cubeSize), cubeSize*3-1, '^')
                '^' -> Position(cubeSize*3 - 1 - pos.x, 0, 'v')
                'v' -> Position(cubeSize*3-1-pos.x, cubeSize*3-1, '<')
                else -> throw Error("Invalid teleport from face 2 when moving ${pos.direction}")
            }
        }

        // 3
        //    'Above -> Left of 1 - Facing Right
        //    'Below' -> Left of 5 - Facing Right
        fun teleport3(pos: Position): Position {
            val cubeSize = jungleMap.getCubeSize()
            return when (pos.direction) {
                '^' -> Position(cubeSize*2, pos.x - cubeSize, 'v')
                'v' -> Position(cubeSize*2, pos.x + cubeSize, '<')
                else -> throw Error("Invalid teleport from face 3 when moving ${pos.direction}")
            }
        }
        // 4
        //    'Right -> Top of 6, facing down
        fun teleport4(pos: Position): Position {
            val cubeSize = jungleMap.getCubeSize()
            return when (pos.direction) {
                '>' -> Position(cubeSize*4-1 - pos.y.mod(cubeSize), cubeSize*2, 'v')
                else -> throw Error("Invalid teleport from face 4 when moving ${pos.direction}")
            }
        }

        // 5
        //    'Left' -> Bottom of 3, facing up
        //    'Below' -> Bottom of 2 - Facing Up
        fun teleport5(pos: Position): Position {
            val cubeSize = jungleMap.getCubeSize()
            return when (pos.direction) {
                '<' -> Position(cubeSize *2-1 -pos.y.mod(cubeSize), cubeSize *2-1, '^')
                'v' -> Position(cubeSize - 1 - pos.x.mod(cubeSize), cubeSize *2-1, '^')
                else -> throw Error("Invalid teleport from face 5 when moving ${pos.direction}")
            }
        }

        fun teleport6(pos: Position): Position {
            val cubeSize = jungleMap.getCubeSize()
            return when (pos.direction) {
                '>' -> Position(cubeSize*3-1, cubeSize - 1 - pos.y.mod(cubeSize), '<')
                '^' -> Position(cubeSize * 3 - 1, cubeSize*2-1 - pos.x.mod(cubeSize), '<')
                'v' -> Position(0, cubeSize*2-1 - pos.x.mod(cubeSize), '>')
                else -> throw Error("Invalid teleport from face 2 when moving ${pos.direction}")
            }
        }

        private fun teleportCube() : Position {
            val newPos = when (val cubeFace = jungleMap.getCubeFace(position.x, position.y)){
                1 ->teleport1(position)
                2 ->teleport2(position)
                3 ->teleport3(position)
                4 ->teleport4(position)
                5 ->teleport5(position)
                6 ->teleport6(position)
                else -> throw Error("Invalid cubeface $cubeFace")
            }
            if (newPos.x==133 || newPos.y==133){
                println("Here")
            }
            return newPos;
        }

        private fun forward(){
            var nextX = position.x
            var nextY = position.y
            var nextDirection = position.direction
            when (nextDirection) {
                '>' -> {
                    nextX++
                }
                '<' -> {
                    nextX--
                }
                'v' -> {
                    nextY++
                }
                else -> {
                    nextY--
                }
            }

            if (jungleMap.offGrid(nextX, nextY)){
                if (cube){
                    val newPosition = teleportCube()
                    nextX = newPosition.x
                    nextY = newPosition.y
                    nextDirection = newPosition.direction
                } else{
                    val newPosition = teleportMethodOne(Position(nextX, nextY, nextDirection))
                    nextX = newPosition.x
                    nextY = newPosition.y
                    nextDirection = newPosition.direction
                }
            }
            if (jungleMap.isWall(nextX, nextY)){
                return
            }

            position.x = nextX
            position.y = nextY
            position.direction = nextDirection
            jungleMap.plotDirection(position)
        }

        private fun forward(times: Int){
            repeat(times){
                forward()
            }
        }

        private fun processInstruction(){
            when (val instruction = instructions.list.removeFirst()){
                "L" -> rotateLeft()
                "R" -> rotateRight()
                else -> forward(instruction.toInt())
            }
            if (debug) {
                displayMap()
            }
        }

        private fun rotateLeft() {
            when (position.direction){
                '>' -> position.direction ='^'
                '^' -> position.direction ='<'
                '<' -> position.direction ='v'
                'v' -> position.direction ='>'
            }
            jungleMap.plotDirection(position)
        }

        private fun rotateRight() {
            when (position.direction){
                '>' -> position.direction ='v'
                '^' -> position.direction ='>'
                '<' -> position.direction ='^'
                'v' -> position.direction ='<'
            }
            jungleMap.plotDirection(position)
        }

        fun getPassword(): Int {
            return (
                    1000 * (position.y+1)
                    + 4 * (position.x+1)
                    + getDirectionValue())
        }

        private fun getDirectionValue(): Int {
                return when (position.direction){
                '>'-> 0
                'v'-> 1
                '<'-> 2
                '^'-> 3
                else -> throw Error("Invalid direction")
            }
        }

        fun processAllInstructions() {
            while (instructions.list.isNotEmpty()){
                processInstruction()
            }
        }
    }
}
