package old
class Day8 {

    class Grid (input : List<String>){
        private val cells = input
            .map { line ->
                line.toCharArray()
                    .map { it.digitToInt() }
                    .toList() }

        fun getRowCount(): Int{
            return cells.size
        }
        fun getColumnCount(): Int{
            return cells[0].size
        }

        fun getCell(row: Int, col: Int): Int{
            return cells[row][col]
        }

        private fun getRow(row:Int) : List<Int>{
            return cells[row]
        }

        private fun getColumn(col:Int) : List<Int>{
            return cells.map { it[col] }
        }

        fun getTop(row: Int, col: Int) : List<Int> {
            return getColumn(col).subList(0, row).reversed()
        }

        fun isVisibleTop(row: Int, col: Int) : Boolean {
            val tree = getCell(row, col)
            return getTop(row, col).none { it >= tree }
        }

        fun getBottom(row: Int, col: Int) : List<Int> {
            return getColumn(col).subList(row+1, getRowCount())
        }

        fun isVisibleBottom(row: Int, col: Int) : Boolean {
            val tree = getCell(row, col)
            return getBottom(row, col).none { it >= tree }
        }

        fun getLeft(row: Int, col: Int) : List<Int> {
            return getRow(row).subList(0, col).reversed()
        }

        fun isVisibleLeft(row: Int, col: Int) : Boolean {
            val tree = getCell(row, col)
            return getLeft(row, col).none { it >= tree }
        }

        fun getRight(row: Int, col: Int) : List<Int> {
            return getRow(row).subList(col+1, getColumnCount())
        }

        fun isVisibleRight(row: Int, col: Int) : Boolean {
            val tree = getCell(row, col)
            return getRight(row, col).none { it >= tree }
        }

        fun getScenicScore(row: Int, col: Int): Int {
            val tree = getCell(row, col)
            val leftScore = countScore(tree, getLeft(row, col))
            val rightScore = countScore(tree, getRight(row, col))
            val topScore = countScore(tree, getTop(row, col))
            val bottomScore = countScore(tree, getBottom(row, col))
            return leftScore * rightScore * topScore * bottomScore
        }

        private fun countScore(tree: Int, otherTrees: List<Int>): Int{
            var score = 0
            for (cell in otherTrees){
                if (cell< tree){
                    score++
                } else{
                    score++
                    return score
                }
            }
            return score
        }

    }

    fun getPart1(commands: List<String>): Int{
        return countEdges(commands) + countInterior(commands)
    }

    fun countEdges(input: List<String>): Int {
        val grid = Grid(input)
        return (grid.getRowCount() *2) + (grid.getColumnCount() *2) - 4
    }

    fun countInterior(input: List<String>): Int {
        val grid = Grid(input)
        var visibleCount = 0

        // Loop through each interior element
        for (row in 1 .. grid.getRowCount()-2 ){
            for (col in 1 .. grid.getColumnCount()-2 ){
                if (grid.isVisibleLeft(row, col)
                    || grid.isVisibleRight(row, col)
                    || grid.isVisibleTop(row, col)
                    || grid.isVisibleBottom(row, col)
                ){
                    visibleCount++
                }
            }
        }
        return visibleCount
    }

    fun getPart2(input: List<String>): Int{
        val grid = Grid(input)
        var maxScore = 0
        for (row in 0 until grid.getRowCount()) {
            for (col in 0 until grid.getColumnCount()) {
              maxScore = maxOf(maxScore, grid.getScenicScore(row,col))
            }
        }
        return maxScore
    }


}