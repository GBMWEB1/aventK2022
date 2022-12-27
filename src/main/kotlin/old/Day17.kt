package old

class Day17 {

    enum class Rock(private val formation : List<String>){
        ONE(listOf(
            "@@@@"
        )),
        TWO(listOf(
            ".@.",
            "@@@",
            ".@.",
        )),
        THREE(listOf(
            "..@",
            "..@",
            "@@@",
        )),
        FOUR(listOf(
            "@",
            "@",
            "@",
            "@"
            )),
        FIVE(listOf(
            "@@",
            "@@",
        ));

        fun getWidth():Int {
            return formation[0].length
        }

        fun getHeight(): Int {
            return formation.size
        }

        fun hit(x: Int, y: Int): Boolean {
            return formation[(formation.size-1) - y][x] == '@'
        }

        fun elements(): Int {
            return formation.sumOf { s ->
                s.toCharArray()
                    .filter { it == '@' }
                    .size
            }
        }
    }

    data class FallingRock(val rock: Rock, var endRow: Int){

        var startRow =  endRow + rock.getHeight()-1

        var startCol=2
        var endCol = startCol + rock.getWidth()-1

        fun present(colPos: Int, rowPos: Int): Boolean{
            // don't ask the rock if the col and row are beyond
            if (colPos< startCol){
                return false
            }
            if (colPos > endCol){
                return false
            }
            // rows are 0 at the bottom, all the way up
            if (rowPos > startRow){ // above
                return false
            }
            if (rowPos < endRow ){
                return false
            }
            // need to ask the Rock
            return rock.hit(colPos - startCol, rowPos - endRow)
        }

        fun moveRight() {
            startCol++
            endCol++
        }
        fun moveLeft() {
            startCol--
            endCol--
        }

        fun drop() {
            startRow--
            endRow--
        }

        fun getTop(): Int {
            return startRow
        }
    }

    class Tower {
        var rocks = mutableListOf<FallingRock>()

        fun addRock(rock: FallingRock){
            rocks.add(rock)
            if (rocks.size>100){
                rocks = rocks.drop(10).toMutableList()
            }
        }

        fun isRock(x: Int, y:Int): Boolean {
            if (rocks.isEmpty()){
                return false
            }
            return rocks.any { it.present(x, y) }
        }

        fun isNotEmpty(): Boolean {
            return rocks.isNotEmpty()
        }

        fun isEmpty(): Boolean {
            return rocks.isEmpty()
        }
    }


    class LargeCave(gasString : String = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>", private val debug: Boolean = false) {
        private var fallingRock: FallingRock? = null

        private val tower = Tower()

        private val gases = ArrayDeque(gasString.toCharArray().toList())

        private fun getGas(): Char {
            val gas = gases.removeFirst()
            gases.addLast(gas)
            return gas
        }

        private val rocks = ArrayDeque(listOf(Rock.ONE, Rock.TWO, Rock.THREE, Rock.FOUR, Rock.FIVE))

        private fun getRockToDrop(): Rock {
            val rock = rocks.removeFirst()
            rocks.addLast(rock)
            return rock
        }

        private fun getLineChars(y: Int): MutableList<Char> {
            val line = mutableListOf('|')

            for (x in 0..6) {
                if (tower.isRock(x, y)) {
                    line.add('#')
                }
                // we have a falling rock
                else if (fallingRock != null) {
                    if (fallingRock!!.present(x, y)) {
                        line.add('@')
                    } else {
                        line.add('.')
                    }
                } else {
                    line.add('.')
                }
            }
            line.add('|')

            return line
        }

        private fun getTopOfRocks(): Int {
            var fallingRockTop = -1
            if (fallingRock != null) {
                fallingRockTop = fallingRock!!.getTop()
            }
            var towerTop = -1
            if (tower.isNotEmpty()) {
                towerTop = tower.rocks.maxOf { it.getTop() }
            }
            return maxOf(towerTop, fallingRockTop)
        }

        private fun getStartY(): Int {
            if (fallingRock == null && tower.isEmpty()) {
                return 3
            }
            return getTopOfRocks()
        }

        fun getDisplay(startY: Int = getStartY(), endY: Int = 0): List<String> {
            val result = mutableListOf<String>()
            for (y in startY downTo endY) {
                result.add(getLineChars(y).joinToString(""))
            }
            result.add("+-------+")
            return result
        }

        private fun printDisplay() {
            getDisplay().forEach { println(it) }
        }

        fun addRock(rock: Rock) {

            var endPos = getTopOfRocks()
            if (endPos == -1) {
                endPos = 3
            } else {
                endPos += 4
            }
            fallingRock = FallingRock(rock, endPos)
        }

        private fun canPushRight(): Boolean {
            if (fallingRock!!.endCol == 6) {
                return false
            }

            for (y in fallingRock!!.endRow..fallingRock!!.startRow) {
                for (x in fallingRock!!.startCol..fallingRock!!.endCol) {
                    if (fallingRock!!.present(x, y) && tower.isRock(x + 1, y)) {
                        return false
                    }
                }
            }
            return true
        }

        fun pushFallingRockRight() {
            if (canPushRight()) {
                fallingRock!!.moveRight()
            }
        }

        private fun canPushLeft(): Boolean {
            if (fallingRock!!.startCol == 0) {
                return false
            }

            for (y in fallingRock!!.endRow..fallingRock!!.startRow) {
                for (x in fallingRock!!.startCol..fallingRock!!.endCol) {
                    if (fallingRock!!.present(x, y) && tower.isRock(x - 1, y)) {
                        return false
                    }
                }
            }
            return true
        }

        private fun pushFallingRockLeft() {
            if (canPushLeft()) {
                fallingRock!!.moveLeft()
            }
        }

        fun pushFallingRock() {
            if (getGas() == '>') {
                pushFallingRockRight()
                if (debug){
                    pushError("Right")
                }
            } else {
                pushFallingRockLeft()
                if (debug){
                    pushError("Left")
                }
            }

        }

        private fun canDrop(): Boolean {
            if (fallingRock!!.endRow == 0) {
                return false
            }

            for (x in fallingRock!!.startCol..fallingRock!!.endCol) {
                for (y in fallingRock!!.endRow..fallingRock!!.startRow) {
                    if (fallingRock!!.present(x, y) && tower.isRock(x, y - 1)) {
                        return false
                    }
                }
            }
            return true
        }

        fun rockFall() {
            if (canDrop()) {
                fallingRock!!.drop()
                if (debug) {
                    pushError("Down")
                }
            } else {
                tower.addRock(fallingRock!!)
                fallingRock = null
            }

        }



        private fun addRock() {
            addRock(getRockToDrop())
        }

        fun dropRock() {
            addRock()
            while (fallingRock != null) {
                pushFallingRock()
                rockFall()
            }
        }

        private fun pushError(dir: String) {
            if (fallingRock != null) {
                val test = getDisplay(fallingRock!!.startRow, fallingRock!!.endRow)
                    .filter { it.contains('@') }.sumOf { s ->
                        s.toCharArray()
                            .filter { it == '@' }
                            .size
                    }
                if (test != fallingRock!!.rock.elements()) {
                    printDisplay()
                    throw Error("Error after a push/drop $dir")
                }
            }
        }

        ///////////
        // Part 2//
        ///////////

        private fun getRepStart(): Int {
            return 5 * gases.size
        }

        private fun getHeightDifferences(): List<Int> {
            val size = 6000

            val result = mutableListOf<Int>()
            var lastHeight = getTopOfRocks() + 1

            repeat(size) {
                dropRock()

                // take a sample
                val height = getTopOfRocks() + 1
                val dif = height - lastHeight
                result.add(dif)
                lastHeight = height
            }
            return result
        }

        fun determineRepetition(): Int {
            repeat(getRepStart()) {
                dropRock()
            }
            val heightDifferences = getHeightDifferences()

            for (chunkSize in 2..heightDifferences.size) {
                val chunks = heightDifferences.chunked(chunkSize).filter { it.size == chunkSize }
                val chunkSums = chunks.map { it.sum() }
                val uniqueNumbers = chunkSums.toSet()
                if (uniqueNumbers.size == 1) {
                    return chunkSize
                }
            }
            return -1
        }

        private fun getRepValues(startHeight: Int, repSize: Int): List<Int> {
            val result = mutableListOf<Int>()
            repeat(repSize) {
                dropRock()
                val height = getTopOfRocks() + 1
                val dif = height - startHeight
                result.add(dif)
            }
            return result
        }


        fun calculateHeight(repLength: Int, blocksDropped: Long): Long {

            repeat(getRepStart()) {
                dropRock()
            }
            val startHeight = getTopOfRocks() + 1

            val cycleHeights = getRepValues(startHeight, repLength)

            val pos = (blocksDropped - getRepStart()) % repLength
            val valueAtPos = cycleHeights[pos.toInt() - 1]

            val iteration = (blocksDropped - getRepStart()) / repLength
            val heightAtEnd = cycleHeights[repLength-1]

            return startHeight + (iteration * heightAtEnd) + valueAtPos
        }
    }
}