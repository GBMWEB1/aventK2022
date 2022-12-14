class Day14 {

    class Cave {
        var maxleft = 500
        var maxRight = 500
        var bottomCave = 0

        private val contents = mutableListOf<ArrayDeque<Char>>()

        init {
            contents.add( ArrayDeque(listOf('+')))
        }
        fun addPath(line:String){
            val points = line.split(" -> ")

            var previousPointX = -1
            var previousPointY = -1

            for (point in points){
                var expandLeft = 0
                var expandRight = 0
                var expandBottom = 0
                val x = point.split(",")[0].toInt()
                val y = point.split(",")[1].toInt()
                if (x< maxleft){
                    expandLeft = maxleft-x
                    maxleft = x
                }
                if (x> maxRight){
                    expandRight = x - maxRight
                    maxRight = x
                }
                if (y> bottomCave){
                    expandBottom = y - bottomCave
                    bottomCave = y
                }
                expandCave(expandLeft, expandRight, expandBottom)
                if (previousPointX>0){
                    drawRockLine(previousPointX, previousPointY, x,y)
                }
                previousPointX = x
                previousPointY = y
            }
        }

        private fun drawRockLine(startX: Int, startY: Int, endX: Int, endY: Int) {
            val lineLeft = minOf(startX, endX) - maxleft
            val lineRight = maxOf(startX, endX) - maxleft
            val lineTop = minOf(startY, endY)
            val lineBottom = maxOf(startY, endY)

            for (rowIndex in lineTop..lineBottom){
                val row = contents[rowIndex]
                for (colIndex in lineLeft .. lineRight){
                    row[colIndex] = '#'
                }
            }
        }

        private fun expandCave(left: Int, right: Int, bottom: Int){
            repeat(left){
                for (row in contents){
                    row.addFirst('.')
                }
            }
            repeat(right){
                for (row in contents){
                    row.addLast('.')
                }
            }
            repeat(bottom){
                val caveWidth = (maxRight - maxleft)+1
                val row = Array(caveWidth){'.'}.toList()
                ArrayDeque<Char>()
                contents.add(ArrayDeque(row))
            }
        }

        fun getDisplay(): List<String> {
            return contents.map { it.joinToString("") }
        }

        private fun isBlocked(x:Int, y: Int) : Boolean{
            val cell = contents[y][x-maxleft]
            if (cell == '#' || cell == 'o'){
                return true
            }
            return false
        }

        private fun dropSand(currentX: Int, currentY:Int):Boolean{
            if (contents[currentY][currentX-maxleft] == 'o'){
               return false
            }
            val nextY = currentY+1
            if (!isBlocked(currentX, nextY)){
                return dropSand(currentX, nextY)
            }

            // check for left
            if (isAbyss(currentX - 1)){
                return false
            }
            if (!isBlocked(currentX - 1, nextY) ){
                return dropSand(currentX - 1, nextY)
            }

            if (isAbyss(currentX + 1)){
                return false
            }
            if (!isBlocked(currentX + 1, nextY)){
                return dropSand(currentX + 1, nextY)
            }
            contents[currentY][currentX-maxleft] = 'o'
            return true
        }

        private fun isAbyss(x: Int): Boolean {
            if (x > maxRight){
                return true
            }
            if (x < maxleft){
                return true
            }
            return false
        }

        fun dropSand(count: Int){
            repeat(count){
                dropSand()
            }
        }
        fun dropSand(){
            val currentX = 500
            val currentY = 0
            dropSand(currentX, currentY)
        }

        fun dropUntilEnd(): Int{
            var pouring = true
            var sandDropped = 0
            while (pouring) {
                sandDropped++
                val currentX = 500
                val currentY = 0
                pouring = dropSand(currentX, currentY)
            }
            return sandDropped-1
        }

        fun addFloor() {
            bottomCave += 2

            var newLeft = 500
            var newRight = 500
            for (y in 0..bottomCave){
                newLeft--
                newRight++
            }
            val expandLeft = maxleft - newLeft
            val expandRight = newRight - maxRight

            maxleft = newLeft
            maxRight = newRight
            expandCave(expandLeft,expandRight,2)

            // now set rock on the bottom
            for (bot in maxleft..maxRight){
                contents[bottomCave][bot-maxleft] = '#'
            }
        }
    }
}