package old

class Day18 {

    data class Cube(val x: Int, val y:Int, val z: Int){

        fun countNeighbours(cubes: List<Cube>) : Int{
            var neighbours = 0
            // above (z+1)
            neighbours += cubes.count { it.x== x && it.y==y && it.z == z+1 }
            // below (z-1)
            neighbours += cubes.count { it.x== x && it.y==y && it.z == z-1 }
            // left (x-1)
            neighbours += cubes.count { it.x== x-1 && it.y==y && it.z == z }
            // right (x+1)
            neighbours += cubes.count { it.x== x+1 && it.y==y && it.z == z }
            // in-front (y-1)
            neighbours += cubes.count { it.x== x && it.y==y-1 && it.z == z }
            // behind (y+1)
            neighbours += cubes.count { it.x== x && it.y==y+1 && it.z == z }
            return neighbours
        }

        companion object{
            fun of(line: String): Cube {
                return line.split(",").map { it.toInt() }.let { Cube(it[0], it[1], it[2]) }
            }
        }
    }

    data class Dimension (val x : Pair<Int, Int>, val y: Pair<Int, Int>, val z: Pair<Int, Int>){
        fun encloses(cube: Cube): Boolean {
            return cube.x in x.first..x.second && cube.y in y.first..y.second && cube.z in z.first..z.second
        }

        fun getStart(): Cube {
            return Cube(x.first, y.first, z.first)
        }
    }


    data class Pond(val cubes : MutableList<Cube>){

        var airPockets = listOf<Cube>()

        private val cubeDimension = Dimension(
            cubes.minOf { it.x } to cubes.maxOf { it.x },
            cubes.minOf { it.y } to cubes.maxOf { it.y },
            cubes.minOf { it.z } to cubes.maxOf { it.z }
        )

        // pond Dimension is one bigger than the cubes all the way round
        private val pondDimension = Dimension(
            cubeDimension.x.first-1 to cubeDimension.x.second+1,
            cubeDimension.y.first-1 to cubeDimension.y.second+1,
            cubeDimension.z.first-1 to cubeDimension.z.second+1)

        fun calculateVisibleSides(): Int {
            val totalCubesAndAir = airPockets + cubes
            return cubes.sumOf { 6 - it.countNeighbours(totalCubesAndAir) }
        }

        private fun inPartOfPond(cube: Cube) : Boolean{
            return pondDimension.encloses(cube)
        }

        private fun getEdgeOfPond(): Cube {
            return pondDimension.getStart()
        }

        fun findAirPockets() {
            val exterior = findSpaces()

            val pockets = mutableListOf<Cube>()
            for (x in cubeDimension.x.first .. cubeDimension.x.second) {
                for (y in cubeDimension.y.first..cubeDimension.y.second) {
                    for (z in cubeDimension.z.first..cubeDimension.z.second) {
                        val cube = Cube(x,y,z)
                        if (!cubes.contains(cube)){
                            if (!exterior.contains(cube)){
                                pockets.add(cube)
                            }
                        }
                    }
                }
            }
            airPockets = pockets.toList()
        }

        private fun findSpaces(): List<Cube>{
            // start from the edge of pond, do a breadth search looking in every dimension
            val start = getEdgeOfPond()
            val visited = mutableSetOf(start)

            var search = mutableSetOf(visited.first())
            while (search.isNotEmpty()){
                search = search.flatMap { exploreSpace(it, visited ) }.toMutableSet()
                visited.addAll(search)
            }
            return visited.toList()
        }

        private fun exploreSpace(currentCube: Cube, visited: MutableSet<Cube>): Set<Cube> {
            val result = mutableSetOf<Cube>()

            val above = currentCube.copy(z=currentCube.z+1)
            if (canExplore(above, visited)){
                result.add(above)
            }
            val below = currentCube.copy(z=currentCube.z-1)
            if (canExplore(below, visited)){
                result.add(below)
            }
            val left = currentCube.copy(x=currentCube.x-1)
            if (canExplore(left, visited)){
                result.add(left)
            }
            val right = currentCube.copy(x=currentCube.x+1)
            if (canExplore(right, visited)){
                result.add(right)
            }
            val front = currentCube.copy(y=currentCube.y-1)
            if (canExplore(front, visited)){
                result.add(front)
            }
            val behind = currentCube.copy(y=currentCube.y+1)
            if (canExplore(behind, visited)){
                result.add(behind)
            }
            return result
        }

        private fun canExplore(cube: Cube, visited: MutableSet<Cube>): Boolean{
            return inPartOfPond(cube) && ! cubes.contains(cube) && !visited.contains(cube)
        }

        companion object{
            fun of(lines: List<String>): Pond {
                return Pond(lines.map { Cube.of(it) }.toMutableList())
            }
        }
    }
}