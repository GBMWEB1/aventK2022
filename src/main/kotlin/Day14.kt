class Day14(private val debug : Boolean = false) {

    private fun printPadded(level: Int, content: String) {
        if (debug){
            val pad = Array(level*2){' '}.joinToString("")
            println("$pad - $content")
        }
    }
}