package old

class Day19 {

    data class BluePrint(
        val oreRore : Int = 4,
        val clayRore : Int = 2,
        val obsRore :Int = 3,
        val obsRclay :Int = 14,
        val geoRore :Int = 2,
        val geoRobs :Int = 7
    ) {
        fun canBuyGeo(state: State): Boolean {
            return state.ore >= geoRore && state.obs >= geoRobs
        }

        fun canBuyObs(state: State): Boolean {
            return state.ore >= obsRore && state.clay >= obsRclay
        }

        fun canBuyOre(state: State): Boolean {
            return state.ore >= oreRore
        }

        fun canBuyClay(state: State): Boolean {
            return state.ore >= clayRore
        }

        fun needObsidianRobots(state: State): Boolean{
            val maxRobots = geoRobs
            if (state.obsRobots>=maxRobots){
                return false
            }
            return true
        }

        fun needClayRobots(state: State): Boolean{
            val maxRobots = obsRclay
            if (state.clayRobots>=maxRobots){
                return false
            }
            return true
        }

        fun needOreRobots(state: State): Boolean{
            val maxRobots = oreRore + clayRore + obsRore + geoRore
            if (state.oreRobots>=maxRobots){
                return false
            }
            return true
        }

        companion object {

            fun of(line: String): BluePrint {
                return BluePrint(
                    oreRore = line.substringAfter("ore robot costs " ).substringBefore(" ore").toInt(),
                    clayRore =line.substringAfter("clay robot costs " ).substringBefore(" ore").toInt(),
                    obsRore =line.substringAfter("obsidian robot costs " ).substringBefore(" ore").toInt(),
                    obsRclay =line.substringAfter("obsidian robot costs " ).substringAfter("and ").substringBefore(" clay").toInt(),
                    geoRore =line.substringAfter("geode robot costs " ).substringBefore(" ore").toInt(),
                    geoRobs =line.substringAfter("geode robot costs " ).substringAfter("and ").substringBefore(" obsidian").toInt()
                )
            }

            fun list(line: List<String>): List<BluePrint> {
                return line.map { of(it) }
            }
        }
    }

    data class State(
        var oreRobots : Int = 1,
        var ore : Int = 0,
        var clay : Int = 0,
        var clayRobots : Int = 0,
        var obs : Int = 0,
        var obsRobots : Int = 0,
        var geo : Int = 0,
        var geoRobots: Int = 0){

        var couldHaveBuiltOreRobot = false
        var couldHaveBuiltClayRobot = false
        var couldHaveBuiltObsRobot = false

        private fun resetCouldHaves(){
            couldHaveBuiltOreRobot = false
            couldHaveBuiltClayRobot = false
            couldHaveBuiltObsRobot = false
        }

        fun buildOreRobot(bluePrint: BluePrint) {
            resetCouldHaves()
            ore -= bluePrint.oreRore
            collectOre()
            oreRobots++
        }

        fun buildClayRobot(bluePrint: BluePrint) {
            resetCouldHaves()
            ore -= bluePrint.clayRore
            collectOre()
            clayRobots++
        }

        fun buildObsidianRobot(bluePrint: BluePrint) {
            resetCouldHaves()
            ore -= bluePrint.obsRore
            clay -= bluePrint.obsRclay
            collectOre()
            obsRobots++
        }

        fun buildGeoRobot(bluePrint: BluePrint) {
            resetCouldHaves()
            ore -= bluePrint.geoRore
            obs -= bluePrint.geoRobs
            collectOre()
            geoRobots++
        }

        fun collectOre(){
            ore += oreRobots
            clay += clayRobots
            obs += obsRobots
            geo += geoRobots
        }
    }

    fun findMaxObs(bluePrint: BluePrint, minutes: Int): Int {
        var options = mutableSetOf(State())
        var i = 0
        while (i < minutes) {
            i++
            options = options.flatMap { exploreOptions(it, bluePrint) }.toMutableSet()
            val maxSoFar = options.maxOf { it.geo }
            val minSoFar = options.minOf { it.geo }
            options = options.filter { isAlive(it,  minutes-i, maxSoFar) }.toMutableSet()

            println("$i - $minSoFar - $maxSoFar ${options.size}")
        }

        return options.maxOf { it.geo }
    }

    private fun isAlive(state: State, timeRemaining: Int, maxSoFar: Int) : Boolean {

        if (state.geo + timeRemaining < maxSoFar){
            return false
        }
        return true
    }

    fun calculateMaxForBlueprints(bluePrints: List<BluePrint>, minutes: Int = 24) :Int{
        val map = bluePrints.map { findMaxObs(it, minutes) }

        return map.mapIndexed { index, i -> (index+1) * i }.sum()
    }

    private fun exploreOptions(state: State, bluePrint: BluePrint): List<State> {
        if (bluePrint.canBuyGeo(state)){
            state.buildGeoRobot(bluePrint)
            return listOf(state)
        }

        val options = mutableListOf<State>()
        if (bluePrint.canBuyObs(state) && bluePrint.needObsidianRobots(state) && !state.couldHaveBuiltObsRobot){
            val newState = state.copy()
            newState.buildObsidianRobot(bluePrint)
            options.add(newState)
            state.couldHaveBuiltObsRobot = true
        }

        if (bluePrint.canBuyOre(state) && bluePrint.needOreRobots(state) && !state.couldHaveBuiltOreRobot){
            val newState = state.copy()
            newState.buildOreRobot(bluePrint)
            options.add(newState)
            state.couldHaveBuiltOreRobot = true
        }

        if (bluePrint.canBuyClay(state) && bluePrint.needClayRobots(state) && !state.couldHaveBuiltClayRobot){
            val newState = state.copy()
            newState.buildClayRobot(bluePrint)
            options.add(newState)
            state.couldHaveBuiltClayRobot = true
        }
        state.collectOre()
        options.add(state)
        return options
    }
}
