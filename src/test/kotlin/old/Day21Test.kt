package old

import Util
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day21Test {

    @Test
    fun testExample(){
        assertEquals(152, Day21().getRoot(Util().readData("day21.txt")))
    }

    @Test
    fun testPart1(){
        //1334684734 is to low
        assertEquals(379578518396784, Day21().getRoot(Util().readData("day21-2.txt")))
    }

    @Test
    fun testExampleHuman(){
        assertEquals(301, Day21().findHumanValue(Util().readData("day21.txt")))
    }

    @Test
    fun testPart2(){
        assertEquals(3353687996514, Day21().findHumanValue(Util().readData("day21-2.txt")))
    }
}
