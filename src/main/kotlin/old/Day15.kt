package old

import kotlin.math.abs

class Day15 {

    data class Beacon(val x: Int, val y: Int)

    data class Sensor(val x: Int, val y: Int, val beacon: Beacon) {
        fun distance(): Int {
            return abs(x - beacon.x) + abs(y - beacon.y)
        }

        fun topX(): Int {
            return x - distance()
        }

        fun topY(): Int {
            return y - distance()
        }

        fun bottomX(): Int {
            return x + distance()
        }

        fun bottomY(): Int {
            return y + distance()
        }

        fun isInArea(posX: Int, posY: Int): Boolean {

            // calculate the distance from the signal
            val posDistance = abs(posX - x) + abs(posY - y)

            return posDistance <= distance()
        }

        fun isSensor(posX: Int, posY: Int): Boolean {
            return posX == x && posY == y
        }

        fun isBeacon(posX: Int, posY: Int): Boolean {
            return posY == beacon.y && posX == beacon.x
        }

        fun jumpToEndOfLine(lineY: Int): Int {
            // for this row, we need to understand the distance to the end of the line.
            // for every y co-ordinate from the Sensor, it represents 1 from the distance.
            return x + abs(distance() - abs(lineY - y))+1
        }

        companion object {

            private fun extractX(input: String): Int {
                return input.substring(
                    input.indexOf("x=") + 2,
                    input.indexOf(",")
                ).toInt()
            }

            private fun extractY(input: String): Int {
                return input.substring(
                    input.indexOf("y=") + 2, input.length
                ).toInt()
            }


            fun of(line: String): Sensor {
                //Sensor at x=2, y=18: closest beacon is at x=-2, y=15
                val signalInput = line.split(":")[0]
                val beaconInput = line.split(":")[1]

                return Sensor(
                    extractX(signalInput), extractY(signalInput),
                    Beacon(extractX(beaconInput), extractY(beaconInput))
                )
            }
        }
    }

    class Grid(lines: List<String>) {
        private val sensors = lines.map { Sensor.of(it) }

        private fun charAt(x: Int, y: Int): Char {
            if (sensors.any { it.isSensor(x, y) }) {
                return 'S'
            }
            if (sensors.any { it.isBeacon(x, y) }) {
                return 'B'
            }

            if (sensors.any { it.isInArea(x, y) }) {
                return '#'
            }
            return '.'
        }

        fun display() {
            val startY = minOf(0, sensors.minOf { it.topY() })
            val endY = sensors.maxOf { it.bottomY() }
            val startX = sensors.minOf { it.topX() }
            val endX = sensors.maxOf { it.bottomX() }

            println(" Grid starts at $startX, $startY")
            for (y in startY..endY) {
                for (x in startX..endX) {
                    print(charAt(x, y))
                }
                println()
            }
        }

        fun countCoverage(row: Int): Int {
            val startX = sensors.minOf { it.topX() }
            val endX = sensors.maxOf { it.bottomX() }

            var count = 0
            for (x in startX..endX) {
                if (charAt(x, row) == '#' || charAt(x, row) == 'S') {
                    count++
                }
            }
            return count

        }

        fun findDistressBeacon(maxGrid: Int): Long {
            for (y in 0..maxGrid) {
                var x = 0
                while (x <= maxGrid){
                    val sensors = sensors.filter { it.isInArea(x, y) }
                    if (sensors.isEmpty()) {
                        return (x * 4000000L) + y
                    }
                    x = sensors[0].jumpToEndOfLine(y)
                }
            }
            error("Oh dear.!")
        }
    }
}