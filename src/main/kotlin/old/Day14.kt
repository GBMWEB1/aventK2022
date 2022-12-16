package old

class Day14 {
    class ExpandingGrid(startX : Int, private val defaultChar : Char = '.'){
        var maxLeft = startX
        var maxRight = startX

        private val contents = mutableListOf<ArrayDeque<Char>>()

        fun getBottom() : Int{
            return contents.size-1
        }

        private fun expandX(x: Int){
            if (x< maxLeft){
                expandLeft(maxLeft-x)
                maxLeft = x
            }
            if (x> maxRight){
                expandRight(x - maxRight)
                maxRight = x
            }
        }

        private fun expandY(y: Int) {
            if (y> getBottom()){
                expandBottom(y - getBottom())
            }
        }

        private fun expandLeft(left: Int){
            repeat(left){
                for (row in contents){
                    row.addFirst(defaultChar)
                }
            }
        }

        private fun expandRight(right: Int){
            repeat(right){
                for (row in contents){
                    row.addLast(defaultChar)
                }
            }
        }

        private fun expandBottom(bottom: Int){
            repeat(bottom){
                val caveWidth = (maxRight - maxLeft)+1
                val row = Array(caveWidth){defaultChar}.toList()
                contents.add(ArrayDeque(row))
            }
        }

        fun getCell(x: Int, y: Int) : Char{
            return contents[y][x-maxLeft]
        }

        fun setCell(x: Int, y: Int, value: Char){
            expandX(x)
            expandY(y)
            contents[y][x-maxLeft] = value
        }

        fun getDisplay(): List<String> {
            return contents.map { it.joinToString("") }
        }

        fun isXOut(x: Int): Boolean {
            if (x > maxRight){
                return true
            }
            if (x < maxLeft){
                return true
            }
            return false
        }
    }

    class Cave {
        var grid = ExpandingGrid(500)

        init {
            grid.setCell(500,0, '+')
        }

        fun addPath(line:String){
            val points = line.split(" -> ")

            var previousPointX = -1
            var previousPointY = -1

            for (point in points){
                val x = point.split(",")[0].toInt()
                val y = point.split(",")[1].toInt()

                if (previousPointX>0){
                    drawRockLine(previousPointX, previousPointY, x,y)
                }
                previousPointX = x
                previousPointY = y
            }
        }

        private fun drawRockLine(startX: Int, startY: Int, endX: Int, endY: Int) {
            val lineLeft = minOf(startX, endX)
            val lineRight = maxOf(startX, endX)
            val lineTop = minOf(startY, endY)
            val lineBottom = maxOf(startY, endY)

            for (rowIndex in lineTop..lineBottom){
                for (colIndex in lineLeft .. lineRight){
                    grid.setCell(colIndex, rowIndex,'#')
                }
            }
        }

        private fun isAvailable(x:Int, y: Int) : Boolean{
            val cell = grid.getCell(x,y)
            if (cell == '#' || cell == 'o'){
                return false
            }
            return true
        }

        private fun dropSand(x: Int, currentY:Int):Boolean{
            if (grid.getCell(x,currentY) == 'o'){
               return false
            }
            val nextY = currentY+1
            if (isAvailable(x, nextY)){
                return dropSand(x, nextY)
            }

            // check left
            if (isAbyss(x - 1)){
                return false
            }
            if (isAvailable(x - 1, nextY) ){
                return dropSand(x - 1, nextY)
            }

            // check right
            if (isAbyss(x + 1)){
                return false
            }
            if (isAvailable(x + 1, nextY)){
                return dropSand(x + 1, nextY)
            }
            grid.setCell(x, currentY, 'o')
            return true
        }

        private fun isAbyss(x: Int): Boolean {
            return grid.isXOut(x)
        }

        //for testing
        fun dropSand(count: Int){
            repeat(count){
                dropSand()
            }
        }
        //for testing
        fun dropSand(){
            dropSand(500, 0)
        }

        fun dropUntilEnd(): Int{
            var pouring = true
            var sandDropped = 0
            while (pouring) {
                sandDropped++
                pouring = dropSand(500, 0)
            }
            return sandDropped-1
        }

        fun addFloor() {
            val newBottom = grid.getBottom() + 2

            var newLeft = 500
            var newRight = 500
            for (y in 0..newBottom){
                newLeft--
                newRight++
            }
            drawRockLine(newLeft, newBottom, newRight, newBottom)
        }
    }
}