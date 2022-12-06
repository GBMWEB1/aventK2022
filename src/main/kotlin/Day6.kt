class Day6 {
    fun getMarker(input: String, markerSize: Int = 4): Int {
        for (pos in markerSize until input.length) {
            if (input.substring(pos-markerSize, pos).toSet().size == markerSize){
                return pos
            }
        }
        return 0
    }
}