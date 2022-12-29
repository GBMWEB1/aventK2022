import Day20.MixingFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day20Test {

    @Test
    fun testSample(){
        val mixingFile = MixingFile(listOf(1, 2, -3, 3, -2, 0, 4))
        assertEquals(listOf(2, 1, -3, 3, -2, 0, 4),  mixingFile.process().map { it.toInt() }) //1 moves between 2 and -3
        assertEquals(listOf(1, -3, 2, 3, -2, 0, 4),  mixingFile.process().map { it.toInt() }) //2 moves between -3 and 3

        //-3 moves between -2 and 0:
        assertEquals(listOf(1, 2, 3, -2, -3, 0, 4),  mixingFile.process().map { it.toInt() })

        //3 moves between 0 and 4:
        assertEquals(listOf(1, 2, -2, -3, 0, 3, 4),  mixingFile.process().map { it.toInt() })

        //-2 moves between 4 and 1:
        assertEquals(listOf(-2, 1, 2, -3, 0, 3, 4),  mixingFile.process().map { it.toInt() })

        //0 does not move:
        assertEquals(listOf(-2, 1, 2, -3, 0, 3, 4),  mixingFile.process().map { it.toInt() })

        //4 moves between -3 and 0:
        assertEquals(listOf(-2, 1, 2, -3, 4, 0, 3),  mixingFile.process().map { it.toInt() })
    }

    @Test
    fun testMoveForward() {
        var mixingFile = MixingFile(listOf(1, 0, 0))
        assertEquals(listOf(0, 1, 0), mixingFile.processAll().map { it.toInt() })
        assertEquals(listOf(0, 0, 1), mixingFile.processAll().map { it.toInt() })
        assertEquals(listOf(0, 1, 0), mixingFile.processAll().map { it.toInt() })
        assertEquals(listOf(0, 0, 1), mixingFile.processAll().map { it.toInt() })

        mixingFile = MixingFile(listOf(2, 0, 0))
        assertEquals(listOf(2, 0, 0), mixingFile.processAll().map { it.toInt() })
        assertEquals(listOf(2, 0, 0), mixingFile.processAll().map { it.toInt() })
        assertEquals(listOf(2, 0, 0), mixingFile.processAll().map { it.toInt() })

        mixingFile = MixingFile(listOf(4, 0, 0))
        assertEquals(listOf(4, 0, 0), mixingFile.processAll().map { it.toInt() })
    }

    @Test
    fun testProcessAll(){
        val mixingFile = MixingFile(listOf(1, 2, -3, 3, -2, 0, 4))
        assertEquals(listOf(-2, 1, 2, -3, 4, 0, 3),  mixingFile.processAll().map { it.toInt() })
    }

    @Test
    fun testGetAtPosition(){
        val mixingFile = MixingFile(listOf(1, 2, -3, 3, -2, 0, 4))
        mixingFile.processAll()
        //-2, 1, 2, -3, 4, 0, 3
        assertEquals(3, mixingFile.getNumberAtPosition(1))
        assertEquals(-2, mixingFile.getNumberAtPosition(2))
        assertEquals(3, mixingFile.getNumberAtPosition(8))
        assertEquals(-2, mixingFile.getNumberAtPosition(9))

        assertEquals(4, mixingFile.getNumberAtPosition(1000))
        assertEquals(-3, mixingFile.getNumberAtPosition(2000))
        assertEquals(2, mixingFile.getNumberAtPosition(3000))

        assertEquals(3, mixingFile.getGroveCoordinates())
    }

    @Test
    fun testPart1(){
        val mixingFile = MixingFile(Util().readData("day20.txt").map { it.toInt() })
        mixingFile.processAll()
        // 2637 is to high
        assertEquals(872, mixingFile.getGroveCoordinates())
    }

    // part 2 sample
    @Test
    fun testPart2Sample(){
        val mixingFile = MixingFile(listOf(1, 2, -3, 3, -2, 0, 4),811589153 )
        assertEquals(listOf(811589153, 1623178306, -2434767459, 2434767459, -1623178306, 0, 3246356612),  mixingFile.getNumbers())

        mixingFile.processAll()
        assertEquals(listOf(0L, -2434767459L, 3246356612L, -1623178306L, 2434767459L, 1623178306L, 811589153L),  mixingFile.getNumbers())
        repeat(9){
            mixingFile.processAll()
        }
        assertEquals(listOf(-2434767459L, 1623178306L, 3246356612L, -1623178306L, 2434767459L, 811589153L, 0),  mixingFile.getNumbers())

        assertEquals(811589153L, mixingFile.getNumberAtPosition(1000))
        assertEquals(2434767459L, mixingFile.getNumberAtPosition(2000))
        assertEquals(-1623178306L, mixingFile.getNumberAtPosition(3000))

        assertEquals(1623178306, mixingFile.getGroveCoordinates())
    }

    @Test
    fun testPart2(){
        val mixingFile = MixingFile(Util().readData("day20.txt").map { it.toInt() },811589153)
        repeat(10) {
            mixingFile.processAll()
        }
        assertEquals(5382459262696L, mixingFile.getGroveCoordinates())
    }
}
