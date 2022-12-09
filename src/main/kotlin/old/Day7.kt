package old

class Day7 {

    data class DeviceFile(val name: String, val size: Int){

        fun display (level: Int){
            val padding = List(level*2) { ' '}.joinToString()
            println("$padding - $name(file, size=$size)")
        }
        companion object {
            fun of(input: String): DeviceFile {
                return input.split(" ").let { DeviceFile(it[1], it[0].toInt()) }
            }
        }
    }

    class DeviceDirectory(
        val name : String,
        val parent: DeviceDirectory?,
        val directories: MutableList<DeviceDirectory> = mutableListOf(),
        val files: MutableList<DeviceFile> = mutableListOf()){

        fun display (level: Int){
            val padding = List(level*2) { ' '}.joinToString()
            println("$padding - $name(dir, size=${getSize()})")
            directories.forEach { it.display(level+1) }
            files.forEach { it.display(level+1) }
        }

        fun getSize(): Int {
            val directorySize = directories.sumOf { it.getSize() }
            return files.sumOf { it.size } + directorySize
        }

        fun getRoot(): DeviceDirectory {
            if (parent!= null){
                return parent.getRoot()
            }
            return this
        }

        fun flatMap(dir: MutableList<DeviceDirectory>){
            dir.add(this)
            directories.forEach { it.flatMap(dir) }
        }
    }

    fun parseCommands(commands: List<String>): DeviceDirectory {
        var currentDirectory = DeviceDirectory("/", null)
        for (command in commands) {
            val parts = command.split(" ")
            if (parts[0] == "$" && parts[1] == "cd" ) {
                currentDirectory = when (parts[2]) {
                    ".." -> currentDirectory.parent!!
                    "/" -> currentDirectory.getRoot()
                    else -> currentDirectory.directories.first { it.name == parts[2] }
                }
            } else if (parts[0] != "$") {
                if (parts[0] == "dir") {
                    currentDirectory.directories.add(DeviceDirectory(parts[1], currentDirectory))
                } else {
                    currentDirectory.files.add(DeviceFile.of(command))
                }
            }
        }
        return currentDirectory.getRoot()
    }


    private fun getDirectories(commands: List<String>): List<DeviceDirectory>{
        val dirs: MutableList<DeviceDirectory> = mutableListOf()
        parseCommands(commands).flatMap(dirs)
        return dirs.sortedBy { it.getSize() }
    }

    fun getPart1(commands: List<String>): Int{
        return getDirectories(commands)
            .map { it.getSize() }
            .filter { it <100000 }
            .sum()
    }
    fun getPart2(commands: List<String>): Int{
        val directories = getDirectories(commands)
        val freeSpace = 70000000 - directories.last().getSize()
        val toFree = 30000000 - freeSpace
        val dir = directories.first {  it.getSize()> toFree }
        return dir.getSize()
    }

}