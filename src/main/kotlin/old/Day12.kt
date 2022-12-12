package old

class Day12 {

    data class Cell(val row: Int, val col: Int, val character: Char, val isStart: Boolean, val isEnd: Boolean) {
        var navigated: Boolean = false
        var top: Cell? = null
        var bottom: Cell? = null
        var left: Cell? = null
        var right: Cell? = null

        fun canVisit(targetCell: Cell?): Boolean {
            if (targetCell == null || targetCell.navigated) {
                return false
            }
            if (isStart) {
                return true
            }
            if (targetCell.isEnd) {
                if (character == 'z') {
                    return true
                }
                return false
            }
            if (targetCell.character - character < 2) {
                return true
            }
            return false
        }

        fun navigate(
            ): List<Cell> {
            if (navigated) {
                return listOf()
            }
            val cellsToNavigate = mutableListOf<Cell>()
            if (canVisit(right)) {
                cellsToNavigate.add(right!!)
            }
            if (canVisit(left)) {
                cellsToNavigate.add(left!!)
            }
            if (canVisit(top)) {
                cellsToNavigate.add(top!!)
            }
            if (canVisit(bottom)) {
                cellsToNavigate.add(bottom!!)
            }
            navigated = true

            return cellsToNavigate
        }

        companion object {
            fun of(character: Char, row: Int, col: Int): Cell {
                val isEnd = character == 'E'
                val isStart = character == 'S'
                return Cell(row, col, character, isStart, isEnd)
            }
        }
    }

    class Grid(input: List<String>) {

        private val cells = input.mapIndexed { row, line ->
            line.toCharArray()
                .mapIndexed { col, character ->
                    Cell.of(character, row, col)
                }
                .toList()
        }

        fun init(): Grid {
            cells.forEachIndexed { rowIndex, row ->
                row.forEachIndexed { colIndex, cell ->
                    // there is a top
                    if (rowIndex > 0) {
                        cell.top = getCell(rowIndex - 1, colIndex)
                    }
                    // there is a bottom
                    if (rowIndex < cells.size - 1) {
                        cell.bottom = getCell(rowIndex + 1, colIndex)
                    }
                    // there is a left
                    if (colIndex > 0) {
                        cell.left = getCell(rowIndex, colIndex - 1)
                    }
                    // there is a right
                    if (colIndex < row.size - 1) {
                        cell.right = getCell(rowIndex, colIndex + 1)
                    }
                }
            }
            return this
        }

        private fun reset(){
            cells.flatten().forEach { it.navigated = false }
        }

        private fun getCell(row: Int, col: Int): Cell {
            return cells[row][col]
        }

        fun navigateToEnd(startCells : List<Cell> = listOf(cells.flatten().first { it.isStart }) ): Int {
            reset()
            var cellsNavigated = startCells
            var cycles = 0
            while (cellsNavigated.none { it.isEnd }) {
                cycles++
                cellsNavigated = cellsNavigated.flatMap { it.navigate() }
            }
            return cycles
        }

        fun navigateFromA() : Int{
            val startCells = cells.flatten().filter { it.isStart || it.character == 'a' }
            return navigateToEnd(startCells)
        }
    }

}