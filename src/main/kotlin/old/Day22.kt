package old

import old.Day22.Direction.*

class Day22 {

    enum class Direction{
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
    }

    data class Face(val number: Int, val xPos: Int, val yPos:Int, val size: Int) {
        fun contains(x: Int, y: Int): Boolean {
            return x >= xPos && x< xPos+size && y >= yPos && y< yPos+size
        }

        fun getRelativeX(pos:Int): Int{
            return pos-xPos
        }
        fun getRelativeY(pos:Int): Int{
            return pos-yPos
        }

        fun fromStartx(pos: Int): Int {
            return xPos + pos
        }

        fun fromEndPosX(pos: Int): Int {
            return xPos + (size-1) - pos
        }

        fun fromStarty(pos: Int): Int {
            return yPos + pos
        }

        fun fromEndPosY(pos: Int): Int {
            return yPos + (size-1) - pos
        }


    }

    data class Join(val from: Face, val fromDir: Direction, val to: Face, val toDir: Direction)

    class JungleMap(val map: List<MutableList<Char>>) {

        val joins = mutableListOf<Join>()

        private val faces = mutableListOf<Face>()

        fun getFace(pos: Int): Face {
            return faces[pos-1]
        }

        fun loadFormation1(){
            val faceSize = 4
            faces.add(Face(1,8,0,faceSize))
            faces.add(Face(2,0,4,faceSize ))
            faces.add(Face(3,4,4,faceSize))
            faces.add(Face(4,8,4,faceSize))
            faces.add(Face(5,8,8,faceSize))
            faces.add(Face(6,12,8,faceSize))

            joins.add(Join(getFace(1), TOP, getFace(2), TOP))
            joins.add(Join(getFace(1), LEFT, getFace(3), TOP))
            joins.add(Join(getFace(1), RIGHT, getFace(6), RIGHT))
            joins.add(Join(getFace(1), BOTTOM, getFace(4), TOP))

            joins.add(Join(getFace(2), TOP, getFace(1), TOP))
            joins.add(Join(getFace(2), LEFT, getFace(6), BOTTOM))
            joins.add(Join(getFace(2), RIGHT, getFace(3), LEFT))
            joins.add(Join(getFace(2), BOTTOM, getFace(5), BOTTOM))

            joins.add(Join(getFace(3), TOP, getFace(1), LEFT))
            joins.add(Join(getFace(3), LEFT, getFace(2), RIGHT))
            joins.add(Join(getFace(3), RIGHT, getFace(4), LEFT))
            joins.add(Join(getFace(3), BOTTOM, getFace(5), LEFT))

            joins.add(Join(getFace(4), TOP, getFace(1), BOTTOM))
            joins.add(Join(getFace(4), LEFT, getFace(3), RIGHT))
            joins.add(Join(getFace(4), RIGHT, getFace(6), TOP))
            joins.add(Join(getFace(4), BOTTOM, getFace(5), TOP))

            joins.add(Join(getFace(5), TOP, getFace(4), BOTTOM))
            joins.add(Join(getFace(5), LEFT, getFace(3), BOTTOM))
            joins.add(Join(getFace(5), RIGHT, getFace(6), LEFT))
            joins.add(Join(getFace(5), BOTTOM, getFace(2), BOTTOM))

            joins.add(Join(getFace(6), TOP, getFace(4), RIGHT))
            joins.add(Join(getFace(6), LEFT, getFace(5), RIGHT))
            joins.add(Join(getFace(6), RIGHT, getFace(1), RIGHT))
            joins.add(Join(getFace(6), BOTTOM, getFace(2), LEFT))
        }
        fun loadFormation2(){
            val faceSize = 50
            faces.add(Face(1,faceSize,0,faceSize))
            faces.add(Face(2,faceSize*2,0,faceSize ))
            faces.add(Face(3,faceSize,faceSize,faceSize))
            faces.add(Face(4,faceSize,faceSize*2,faceSize))
            faces.add(Face(5,0,faceSize*2,faceSize))
            faces.add(Face(6,0,faceSize*3,faceSize))

            joins.add(Join(getFace(1), TOP, getFace(6), LEFT))
            joins.add(Join(getFace(1), LEFT, getFace(5), LEFT))
            joins.add(Join(getFace(1), RIGHT, getFace(2), LEFT))
            joins.add(Join(getFace(1), BOTTOM, getFace(3), TOP))

            joins.add(Join(getFace(2), TOP, getFace(6), BOTTOM))
            joins.add(Join(getFace(2), LEFT, getFace(1), RIGHT))
            joins.add(Join(getFace(2), RIGHT, getFace(4), RIGHT))
            joins.add(Join(getFace(2), BOTTOM, getFace(3), RIGHT))

            joins.add(Join(getFace(3), TOP, getFace(1), BOTTOM))
            joins.add(Join(getFace(3), LEFT, getFace(5), TOP))
            joins.add(Join(getFace(3), RIGHT, getFace(2), BOTTOM))
            joins.add(Join(getFace(3), BOTTOM, getFace(4), TOP))

            joins.add(Join(getFace(4), TOP, getFace(3), BOTTOM))
            joins.add(Join(getFace(4), LEFT, getFace(5), RIGHT))
            joins.add(Join(getFace(4), RIGHT, getFace(2), RIGHT))
            joins.add(Join(getFace(4), BOTTOM, getFace(6), RIGHT))

            joins.add(Join(getFace(5), TOP, getFace(3), LEFT))
            joins.add(Join(getFace(5), LEFT, getFace(1), LEFT))
            joins.add(Join(getFace(5), RIGHT, getFace(4), LEFT))
            joins.add(Join(getFace(5), BOTTOM, getFace(6), TOP))

            joins.add(Join(getFace(6), TOP, getFace(5), BOTTOM))
            joins.add(Join(getFace(6), LEFT, getFace(1), TOP))
            joins.add(Join(getFace(6), RIGHT, getFace(4), BOTTOM))
            joins.add(Join(getFace(6), BOTTOM, getFace(2), TOP))
        }

        fun getCubeFace(x: Int, y:Int): Int{
            return faces.first { it.contains(x,y) }.number
        }

        fun isWall(x: Int, y:Int): Boolean {
           return map[y][x] == '#'
        }

        fun plotDirection(pos : Position) {
            map[pos.y][pos.x] = pos.direction
        }

        fun offGrid(x: Int, y: Int): Boolean {
            if (y<0 || x<0){
                return true
            }
            if (y >= map.size || x>= map[y].size){
                return true
            }
            if (map[y][x]==' '){
                return true
            }
            return false
        }

        fun getStartY(x: Int): Int {
            map.forEachIndexed { index, list ->
                if (list[x] != ' ') {
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
                if (map[y][x] != ' ') {
                    return x
                }
            }
            return -1
        }

        fun getLastY(x: Int): Int {
            map.reversed().forEachIndexed { index, list ->
                if (x<list.size) {
                    if (list[x] != ' ') {
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

    data class Position(var x : Int, var y: Int, var direction: Char){
        fun getDirectionFromChar(): Direction {
            return when (direction){
                '<' -> LEFT
                '>' -> RIGHT
                '^' -> TOP
                'v' -> BOTTOM
                else -> throw Error("Invalid direction $direction")
            }
        }
    }

    class Navigator(val jungleMap: JungleMap,
                    private val instructions: Instructions,
                    private val debug: Boolean) {


        private var position = Position(jungleMap.getStartX(), 0, '>')

        private var cube = false

        fun setCube1(){
            cube = true
            jungleMap.loadFormation1()
        }
        fun setCube2() {
            cube = true
            jungleMap.loadFormation2()

        }

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

        fun teleport1(pos: Position): Position {
            val startFace = jungleMap.getFace(1)
            return teleport2(pos, startFace)
        }

        fun teleport2(pos: Position, startFace: Face): Position {

            val join = jungleMap.joins.find { it.from == startFace && it.fromDir == pos.getDirectionFromChar() }
                ?: throw Error("Unable to find join")

            if (join.fromDir == TOP && join.toDir== TOP){
                return Position(join.to.fromEndPosX(startFace.getRelativeX(pos.x)), join.to.yPos, 'v')
            }
            if (join.fromDir == TOP && join.toDir== LEFT){
                return Position(join.to.xPos, join.to.fromStarty(startFace.getRelativeX(pos.x)), '>')
            }
            if (join.fromDir == TOP && join.toDir== RIGHT){
                return Position(join.to.fromEndPosX(0), join.to.fromEndPosY(startFace.getRelativeX(pos.x)), '<')
            }
            // From top to bottom
            if (join.fromDir == TOP && join.toDir== BOTTOM){
                return Position(join.to.fromStartx(startFace.getRelativeX(pos.x)), join.to.fromEndPosY(0), '^')
            }

            // Top to bottom
            if (join.fromDir == LEFT && join.toDir== TOP){
                return Position(join.to.xPos+ startFace.getRelativeY(pos.y), join.to.yPos, 'v')
            }
            if (join.fromDir == LEFT && join.toDir== BOTTOM){
                return Position(join.to.fromEndPosX(startFace.getRelativeY(pos.y)), join.to.fromEndPosY(0), '^')
            }
            // new
            if (join.fromDir == LEFT && join.toDir== LEFT){
                return Position(join.to.xPos, join.to.fromEndPosY(startFace.getRelativeY(pos.y)), '>')
            }

            if (join.fromDir == RIGHT && join.toDir== RIGHT){
                return Position(join.to.fromEndPosX(0), join.to.fromEndPosY(startFace.getRelativeY(pos.y) ), '<')
            }
            if (join.fromDir == RIGHT && join.toDir== TOP){
                return Position(join.to.fromEndPosX(startFace.getRelativeY(pos.y)), join.to.yPos, 'v')
            }

            //new
            if (join.fromDir == RIGHT && join.toDir== BOTTOM){
                return Position(join.to.fromStartx(startFace.getRelativeY(pos.y)), join.to.fromEndPosY(0), '^')
            }

            if (join.fromDir == BOTTOM && join.toDir== LEFT){
                return Position(join.to.xPos, join.to.fromEndPosY(startFace.getRelativeX(pos.x)), '>')
            }
            if (join.fromDir == BOTTOM && join.toDir== BOTTOM){
                return Position(join.to.fromEndPosX(startFace.getRelativeX(pos.x)), join.to.fromEndPosY(0), '^')
            }
            // new
            if (join.fromDir == BOTTOM && join.toDir== RIGHT){
                return Position(join.to.fromEndPosX(0), join.to.fromStarty(startFace.getRelativeX(pos.x)), '<')
            }
            // new
            if (join.fromDir == BOTTOM && join.toDir== TOP){
                return Position(join.to.xPos+ startFace.getRelativeX(pos.x), join.to.yPos, 'v')
            }


            throw Error("Unable to teleport from ${startFace.number} $join")
        }

        fun teleport3(pos: Position): Position {
            return teleport2(pos, jungleMap.getFace(3))
        }

        // 4
        //    'Right -> Top of 6, facing down
        fun teleport4(pos: Position): Position {
            return teleport2(pos, jungleMap.getFace(4))
        }

        fun teleport5(pos: Position): Position {
           return teleport2(pos, jungleMap.getFace(5))
        }

        fun teleport6(pos: Position): Position {
            return teleport2(pos, jungleMap.getFace(6))
        }

        private fun teleportCube() : Position {
            val cubeFace = jungleMap.getCubeFace(position.x, position.y)
            val face = jungleMap.getFace(cubeFace)
            return teleport2(position, face)
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

            if (cube && jungleMap.offGrid(nextX, nextY)){
                val newPosition = teleportCube()
                nextX = newPosition.x
                nextY = newPosition.y
                nextDirection = newPosition.direction
            }
            if (!cube && jungleMap.offGrid(nextX, nextY)){
                val newPosition = teleportMethodOne(Position(nextX, nextY, nextDirection))
                nextX = newPosition.x
                nextY = newPosition.y
                nextDirection = newPosition.direction

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
