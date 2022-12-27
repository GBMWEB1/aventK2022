package old

class Day16 {

    data class Valve(val name: String, val flow: Int, val tunnelsCodes: List<String>) {

        lateinit var distances: Map<String, Int>
        lateinit var tunnels: List<Valve>

        fun join(valves: List<Valve>) {
            tunnels = tunnelsCodes.map { tunnelCode ->
                valves.filter {
                    it.name == tunnelCode
                }.first()
            }
        }

        fun travel(tunnelsVisited : MutableList<String>): List<Valve>{
            tunnelsVisited.add(name)
            return tunnels
                .filter { !tunnelsVisited.contains(it.name) }
        }

        fun populateDistances(valves: List<Valve>){
            distances = valves.map { it.name to travelTo(it.name) }.toMap()
        }

        fun travelTo(codeToCheck: String): Int {
            var tunnelsToCheck = listOf(this)
            var found = name == codeToCheck;
            var distance = 0;
            var tunnelsChecked = mutableListOf<String>()
            while (!found){
                distance += 1;
                tunnelsToCheck = tunnelsToCheck.flatMap { it.travel(tunnelsChecked) }
                if (tunnelsToCheck.any { it.name == codeToCheck }){
                    return distance;
                }
            }
            return distance;
        }

        // value getting to a node is dependent on the sum of all its other nodes.
        fun getValue(openedValves: List<String>, allValves : List<Valve>) : Int {

            val values = allValves.map { entry -> entry.name to entry.flow}.toMap();

            var total = distances
                .filter { !openedValves.contains(it.key) }
                .map { values[it.key]!!.minus(it.value)  }
                .sum()

            if (!openedValves.contains(name)){
                total += flow;
            }
            return total;
        }

        companion object {
            fun of(it: String): Valve {
                val name = it.substring(6,8);
                val rate = it.substring( it.indexOf("=")+1,it.indexOf(";"))
                var tunnelStart = it.indexOf("valves")+6
                if (tunnelStart == 5){
                    tunnelStart = it.indexOf("valve")+5
                }
                var tunnels = it.substring(tunnelStart+1)

                return Valve(name, rate.toInt(),tunnels.split(", "))
            }
        }
    }

    class State(
        var currentValve: Valve,
        var flow: Int = 0,
        var opened : MutableList<String> = mutableListOf(),
        var pressure : Int = 0
    )


    fun passMinute(state: State, allValves: List<Valve>){
        // up the pressure based on current flow
        state.pressure += state.flow;

        val tunnelValues = state.currentValve.tunnels.map { it.getValue(state.opened, allValves) }

        if (!state.opened.contains(state.currentValve.name)){
            if (state.currentValve.flow >= tunnelValues.max()){
                state.flow += state.currentValve.flow
                state.opened.add(state.currentValve.name)
                return;
            }
        }
        val maxIdx = tunnelValues.indices.maxBy { tunnelValues[it] }
        state.currentValve = state.currentValve.tunnels[maxIdx]
    }
}