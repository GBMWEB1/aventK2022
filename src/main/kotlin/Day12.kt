class Day12 {

    data class Cell(val row: Int, val col: Int, val character: Char, val isStart: Boolean, val isEnd: Boolean) {
        var navigated: Boolean = false
        var top: Cell? = null
        var bottom: Cell? = null
        var left: Cell? = null
        var right: Cell? = null

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

        private fun getCell(row: Int, col: Int): Cell {
            return cells[row][col]
        }

        private fun canVisit(targetCell: Cell?, currentCell: Cell): Boolean {
            if (targetCell == null || targetCell.navigated) {
                return false
            }
            if (currentCell.isStart) {
                return true
            }
            if (targetCell.isEnd) {
                if (currentCell.character == 'z') {
                    return true
                }
                return false
            }
            if (targetCell.character - currentCell.character < 2) {
                return true
            }
            return false
        }

        private fun reset(){
            cells.flatten().forEach { it.navigated = false }
        }

        fun navigateToEnd(startCells : List<Cell> = listOf(cells.flatten().first { it.isStart }) ): Int {
            reset()
            var toNavigate = startCells
            var cycles = 0
            while (toNavigate.none { it.isEnd }) {
                cycles++
                toNavigate = toNavigate.flatMap { navigate(it) }
            }
            return cycles
        }

        fun countFromA() : Int{
            val startCells = cells.flatten().filter { it.isStart || it.character == 'a' }
            return navigateToEnd(startCells)
        }

        private fun navigate(
            cell: Cell
        ): List<Cell> {
            if (cell.navigated) {
                return listOf()
            }
            val cellsToNavigate = mutableListOf<Cell>()
            if (canVisit(cell.right, cell)) {
                cellsToNavigate.add(cell.right!!)
            }
            if (canVisit(cell.left, cell)) {
                cellsToNavigate.add(cell.left!!)
            }
            if (canVisit(cell.top, cell)) {
                cellsToNavigate.add(cell.top!!)
            }
            if (canVisit(cell.bottom, cell)) {
                cellsToNavigate.add(cell.bottom!!)
            }
            cell.navigated = true

            return cellsToNavigate
        }
    }

}