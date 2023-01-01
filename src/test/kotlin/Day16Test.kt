import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day16Test {

    @Test
    fun testSample() {
        val valves = Util().readData("day16.txt").map { Day16.Valve.of(it) }
        valves.forEach {
            it.join(valves)
        }
        val valvesWithFlow = valves.filter { it.flow>0 }
        valves.forEach {
            it.populateDistances(valvesWithFlow)
        }
        val maxPressure  = Day16().openValves(valves)
        assertEquals(1651, maxPressure)
    }

    @Test
    fun testPart1() {
        val valves = Util().readData("day16-2.txt").map { Day16.Valve.of(it) }
        valves.forEach {
            it.join(valves)
        }
        val valvesWithFlow = valves.filter { it.flow>0 }
        valves.forEach {
            it.populateDistances(valvesWithFlow)
        }
        val maxPressure  = Day16().openValves(valves)
        //1641
        assertEquals(1641, maxPressure)
    }

    @Test
    fun testSamplePart2() {
        val valves = Util().readData("day16.txt").map { Day16.Valve.of(it) }
        valves.forEach {
            it.join(valves)
        }
        val valvesWithFlow = valves.filter { it.flow>0 }
        valves.forEach {
            it.populateDistances(valvesWithFlow)
        }
        val maxPressure  = Day16().openValves(valves, true)
        assertEquals(1707, maxPressure)
    }

    @Test
    fun testPart2() {
        val valves = Util().readData("day16-2.txt").map { Day16.Valve.of(it) }
        valves.forEach {
            it.join(valves)
        }
        val valvesWithFlow = valves.filter { it.flow>0 }
        valves.forEach {
            it.populateDistances(valvesWithFlow)
        }
        val maxPressure  = Day16().openValves(valves, true)
        assertEquals(2261, maxPressure)
    }


}
