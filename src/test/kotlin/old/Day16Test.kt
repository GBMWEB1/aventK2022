package old

import Util
import org.junit.jupiter.api.Test

class Day16Test {

//    @Test
//    fun testCreate() {
//        val tunnels = Util().readData("day16.txt").map { old.Day16.Valve.of(it) }
//        tunnels.forEach { it.join(tunnels) }
//        assertEquals(0, tunnels[0].flow)
//        assertEquals("AA", tunnels[0].name)
//        assertEquals("DD", tunnels[0].tunnels[0].name)
//    }
    @Test
    fun testMinute1() {
        val tunnels = Util().readData("day16.txt").map { Day16.Valve.of(it) }
        tunnels.forEach {
            it.join(tunnels)
        }
        tunnels.forEach {
            it.populateDistances(tunnels)
        }

    val state = Day16.State(
        currentValve = tunnels.first { it.name == "AA" },
        flow = 0,
        pressure = 0,
        opened = mutableListOf()
    )

    Day16().passMinute(state, tunnels);
    Day16().passMinute(state, tunnels);
    Day16().passMinute(state, tunnels);

    println(state)
    }

}
