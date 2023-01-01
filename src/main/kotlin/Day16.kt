import Day16.Valve as Valve1

class Day16 {

    data class Valve(val name: String, val flow: Int, val tunnelsCodes: List<String>) {

        lateinit var distances: Map<String, Int>
        private lateinit var tunnels: List<Valve1>

        fun join(valves: List<Valve1>) {
            tunnels = tunnelsCodes.map { tunnelCode ->
                valves.first {
                    it.name == tunnelCode
                }
            }
        }

        private fun travel(tunnelsVisited: MutableList<String>): List<Valve1> {
            tunnelsVisited.add(name)
            return tunnels
                .filter { !tunnelsVisited.contains(it.name) }
        }

        fun populateDistances(valves: List<Valve1>) {
            distances = valves
                .filter { it.name != name }
                .associate { it.name to travelTo(it.name) }
        }

        private fun travelTo(codeToCheck: String): Int {
            var tunnelsToCheck = listOf(this)
            val found = name == codeToCheck
            var distance = 0
            val tunnelsChecked = mutableListOf<String>()
            while (!found) {
                distance += 1
                tunnelsToCheck = tunnelsToCheck.flatMap { it.travel(tunnelsChecked) }
                if (tunnelsToCheck.any { it.name == codeToCheck }) {
                    return distance
                }
            }
            throw Error("Unable to travel to $codeToCheck")
        }

        companion object {
            fun of(it: String): Valve1 {
                val name = it.substring(6, 8)
                val rate = it.substring(it.indexOf("=") + 1, it.indexOf(";"))
                var tunnelStart = it.indexOf("valves") + 6
                if (tunnelStart == 5) {
                    tunnelStart = it.indexOf("valve") + 5
                }
                val tunnels = it.substring(tunnelStart + 1)

                return Valve1(name, rate.toInt(), tunnels.split(", "))
            }
        }
    }

    class Journey(
        var dest: Valve1,
        var timeToDestination: Int = 0)

    class State(
        var you: Journey,
        var elephant: Journey,
        var flow: Int = 0,
        var opened: List<String> = listOf(),
        var pressure: Int = 0
    ) {
        fun finalPressure(minutesLeft: Int) : Int {
            return pressure + (flow * minutesLeft)
        }

        fun getYourNextDestinations(valves: List<Valve1>): List<Journey>{
            return valves
                .filter { it.flow>0 }
                .filter { !opened.contains(it.name) }
                .filter { it.name !=elephant.dest.name }
                .map { nextDestination ->
                    Journey(
                        dest = nextDestination,
                        timeToDestination = you.dest.distances[nextDestination.name]!! - 1,
                    )
                }
        }

        fun getElephantNextDestinations(valves: List<Valve1>): List<Journey> {
            return valves
                .filter { it.flow>0 }
                .filter { !opened.contains(it.name) }
                .filter { it.name !=you.dest.name }
                .map { nextDestination ->
                    Journey(
                        dest = nextDestination,
                        timeToDestination = elephant.dest.distances[nextDestination.name]!! - 1,
                    )
                }
        }

        var openedAll: Boolean = false
    }

    fun openValves(valves: List<Valve1>, elephant:Boolean = false): Int {
        val startState = State(
            you = Journey(dest = valves.first { it.name == "AA" }),
            elephant = Journey(dest = valves.first { it.name == "AA" }),
            flow = 0,
            pressure = 0,
            opened = mutableListOf()
        )
        var states = listOf(startState)
        var minute = 0
        var totalMinutes = 30
        if (elephant){
            totalMinutes = 26
        }
        while (minute < totalMinutes) {
            states.forEach { it.pressure = it.pressure + it.flow }
            states = states.flatMap { passYourMinute(it, valves ) }
            if (elephant){
                states = states.flatMap { passElephantMinute(it, valves ) }
            }

            println("$minute : ${states.size}")
            minute++
            val maxSoFarFinal = states.filter { it.openedAll }.maxOfOrNull { it.finalPressure(totalMinutes-minute) }
            val maxPressure = states.maxOfOrNull { it.pressure }

            states = states.filter { keepState(it, totalMinutes-minute, maxSoFarFinal, maxPressure) }
        }
        return states.maxOf { it.pressure }
    }

    private fun keepState(state: State, timeLeft: Int, maxSoFar: Int?, maxPressure: Int?): Boolean {
        if (timeLeft.mod(6)==0){
            if (maxPressure!! - state.pressure > 50) {
                return false
            }
        }

        if (state.openedAll && maxSoFar!= null && state.finalPressure(timeLeft)< maxSoFar){
            return false
        }
        return true
    }


    private fun passElephantMinute(state: State, valves: List<Valve1>): List<State> {
        // opened everything
        if (state.openedAll) {
            return listOf(state)
        }

        val states = mutableListOf<State>()

        var elephantNextDestination = listOf<Journey>()

        // travel
        if (state.elephant.timeToDestination>0) {
            state.elephant.timeToDestination--
        }
        else if (state.elephant.dest.flow > 0 && !state.opened.contains(state.elephant.dest.name)) {
            // open the valve
            state.flow += state.elephant.dest.flow
            state.opened = state.opened + state.elephant.dest.name
            state.openedAll = state.opened.size == valves.count { it.flow > 0 }
        }
        else{
            elephantNextDestination = state.getElephantNextDestinations(valves)
        }

        if (elephantNextDestination.isEmpty()){
            states.add(state)
        } else{
            elephantNextDestination.forEach {
                states.add(
                    State(
                        you = Journey(
                            dest = state.you.dest,
                            timeToDestination = state.you.timeToDestination
                        ),
                        elephant = it,
                        flow = state.flow,
                        pressure = state.pressure,
                        opened = state.opened
                    )
                )
            }
        }

        return states
    }

    private fun passYourMinute(state: State, valves: List<Valve1>): List<State> {
        if (state.openedAll) {
            return listOf(state)
        }

        // travel
        if (state.you.timeToDestination>0) {
            state.you.timeToDestination--
            return listOf(state)
        }

        if (state.you.dest.flow > 0 && !state.opened.contains(state.you.dest.name)) {
            // open the valve
            state.flow += state.you.dest.flow
            state.opened = state.opened + state.you.dest.name
            state.openedAll = state.opened.size == valves.count { it.flow > 0 }
            return listOf(state)
        }

        val youNextDestination = state.getYourNextDestinations(valves)
        if (youNextDestination.isEmpty()){
            return listOf(state)
        }

        val states = mutableListOf<State>()
        youNextDestination.forEach {
            states.add(
                State(
                    you = it,
                    elephant = Journey(
                        dest = state.elephant.dest,
                        timeToDestination = state.elephant.timeToDestination
                    ),
                    flow = state.flow,
                    pressure = state.pressure,
                    opened = state.opened
                )
            )
        }


        return states
    }
}