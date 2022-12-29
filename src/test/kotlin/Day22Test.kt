import Day22
import Util
import Day22.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day22Test {

    @Test
    fun testDisplayMap(){
        val navigator = Day22.Navigator.fromList(Util().readData("day22.txt"))
        navigator.displayMap()
    }
    @Test
    fun testStartX(){
        val navigator = Day22.Navigator.fromList(Util().readData("day22.txt"))
        assertEquals(8, navigator.jungleMap.getStartX())
    }

    @Test
    fun testProcessAllInstructions(){
        val navigator = Day22.Navigator.fromList(Util().readData("day22.txt"), true)
        navigator.processAllInstructions();
        assertEquals(6032, navigator.getPassword())
    }

    @Test
    fun testPart1(){
        val navigator = Day22.Navigator.fromList(Util().readData("day22-2.txt"), false)
        navigator.processAllInstructions();
        assertEquals(103224, navigator.getPassword())
    }

    // Rules are
    @Test
    fun testProcessAllInstructionsPart2(){
        val navigator = Day22.Navigator.fromList(Util().readData("day22.txt"), true)
        navigator.setCube1()
        navigator.processAllInstructions();
        assertEquals(5031, navigator.getPassword())
    }

    @Test
    fun testTeleportFrom1(){
        val navigator = Day22.Navigator.fromList(Util().readData("day22.txt"))
        navigator.setCube1()

        //    'Left' -> Top of 3 - Facing Down
        assertEquals(Position(4,4,'v'), navigator.teleport1(Position(8,0, '<')))
        assertEquals(Position(5,4,'v'), navigator.teleport1(Position(8,1, '<')))
        assertEquals(Position(6,4,'v'), navigator.teleport1(Position(8,2, '<')))
        assertEquals(Position(7,4,'v'), navigator.teleport1(Position(8,3, '<')))

        //    'Above -> Top of 2 - Facing Down
        assertEquals(Position(3,4,'v'), navigator.teleport1(Position(8,0, '^')))
        assertEquals(Position(2,4,'v'), navigator.teleport1(Position(9,0, '^')))
        assertEquals(Position(1,4,'v'), navigator.teleport1(Position(10,0, '^')))
        assertEquals(Position(0,4,'v'), navigator.teleport1(Position(11,0, '^')))

        //    'Right -> Right of 6, Facing Left
        assertEquals(Position(15,11,'<'), navigator.teleport1(Position(11,0, '>')))
        assertEquals(Position(15,10,'<'), navigator.teleport1(Position(11,1, '>')))
        assertEquals(Position(15,9,'<'), navigator.teleport1(Position(11,2, '>')))
        assertEquals(Position(15,8,'<'), navigator.teleport1(Position(11,3, '>')))
    }

    @Test
    fun testTeleportFrom2(){
        val navigator = Day22.Navigator.fromList(Util().readData("day22.txt"))
        navigator.setCube1()

        //    'Left' -> Bottom of 6, facing up
        assertEquals(Position(15,11,'^'), navigator.teleport2(Position(0,4, '<'), navigator.jungleMap.getFace(2)))
        assertEquals(Position(14,11,'^'), navigator.teleport2(Position(0,5, '<'), navigator.jungleMap.getFace(2)))
        assertEquals(Position(13,11,'^'), navigator.teleport2(Position(0,6, '<'), navigator.jungleMap.getFace(2)))
        assertEquals(Position(12,11,'^'), navigator.teleport2(Position(0,7, '<'), navigator.jungleMap.getFace(2)))

        //    'Above -> Top of 1 - Facing Down
        assertEquals(Position(11,0,'v'), navigator.teleport2(Position(0,4, '^'), navigator.jungleMap.getFace(2)))
        assertEquals(Position(10,0,'v'), navigator.teleport2(Position(1,4, '^'), navigator.jungleMap.getFace(2)))
        assertEquals(Position(9,0,'v'), navigator.teleport2(Position(2,4, '^'), navigator.jungleMap.getFace(2)))
        assertEquals(Position(8,0,'v'), navigator.teleport2(Position(3,4, '^'), navigator.jungleMap.getFace(2)))

        //    'Below' -> Bottom of 5 - Facing Up
        assertEquals(Position(11,11,'^'), navigator.teleport2(Position(0,7, 'v'), navigator.jungleMap.getFace(2)))
        assertEquals(Position(10,11,'^'), navigator.teleport2(Position(1,7, 'v'), navigator.jungleMap.getFace(2)))
        assertEquals(Position(9,11,'^'), navigator.teleport2(Position(2,7, 'v'), navigator.jungleMap.getFace(2)))
        assertEquals(Position(8,11,'^'), navigator.teleport2(Position(3,7, 'v'), navigator.jungleMap.getFace(2)))
    }

    @Test
    fun testTeleportFrom3(){
        val navigator = Day22.Navigator.fromList(Util().readData("day22.txt"))
        navigator.setCube1()

        //    'Above -> Left of 1 - Facing Right
        assertEquals(Position(8,0,'>'), navigator.teleport3(Position(4,4, '^')))
        assertEquals(Position(8,1,'>'), navigator.teleport3(Position(5,4, '^')))
        assertEquals(Position(8,2,'>'), navigator.teleport3(Position(6,4, '^')))
        assertEquals(Position(8,3,'>'), navigator.teleport3(Position(7,4, '^')))

        //    'Below' -> Left of 5 - Facing Right
        assertEquals(Position(8,8,'>'), navigator.teleport3(Position(7,7, 'v')))
        assertEquals(Position(8,9,'>'), navigator.teleport3(Position(6,7, 'v')))
        assertEquals(Position(8,10,'>'), navigator.teleport3(Position(5,7, 'v')))
        assertEquals(Position(8,11,'>'), navigator.teleport3(Position(4,7, 'v')))
    }

    @Test
    fun testTeleportFrom4(){
        val navigator = Day22.Navigator.fromList(Util().readData("day22.txt"))
        navigator.setCube1()

        //    'Right -> Top of 6, facing down
        assertEquals(Position(15,8,'v'), navigator.teleport4(Position(11,4, '>')))
        assertEquals(Position(14,8,'v'), navigator.teleport4(Position(11,5, '>')))
        assertEquals(Position(13,8,'v'), navigator.teleport4(Position(11,6, '>')))
        assertEquals(Position(12,8,'v'), navigator.teleport4(Position(11,7, '>')))
    }

    @Test
    fun testTeleportFrom5(){
        val navigator = Day22.Navigator.fromList(Util().readData("day22.txt"))
        navigator.setCube1()

        //    'Left' -> Bottom of 3, facing up
        assertEquals(Position(7,7,'^'), navigator.teleport5(Position(8,8, '<')))
        assertEquals(Position(6,7,'^'), navigator.teleport5(Position(8,9, '<')))
        assertEquals(Position(5,7,'^'), navigator.teleport5(Position(8,10, '<')))
        assertEquals(Position(4,7,'^'), navigator.teleport5(Position(8,11, '<')))


        //  'Below' -> Bottom of 2 - Facing Up
        assertEquals(Position(3,7,'^'), navigator.teleport5(Position(8,15, 'v')))
        assertEquals(Position(2,7,'^'), navigator.teleport5(Position(9,15, 'v')))
        assertEquals(Position(1,7,'^'), navigator.teleport5(Position(10,15, 'v')))
        assertEquals(Position(0,7,'^'), navigator.teleport5(Position(11,15, 'v')))
    }


    @Test
    fun testTeleportFrom6(){
        val navigator = Day22.Navigator.fromList(Util().readData("day22.txt"))
        navigator.setCube1()

        //    'Above -> Right of 4, facing left
        assertEquals(Position(11,7,'<'), navigator.teleport6(Position(12,8, '^')))
        assertEquals(Position(11,6,'<'), navigator.teleport6(Position(13,8, '^')))
        assertEquals(Position(11,5,'<'), navigator.teleport6(Position(14,8, '^')))
        assertEquals(Position(11,4,'<'), navigator.teleport6(Position(15,8, '^')))
//
        //    'Below' -> Left of 2, facing right
        assertEquals(Position(0,7,'>'), navigator.teleport6(Position(12,11, 'v')))
        assertEquals(Position(0,6,'>'), navigator.teleport6(Position(13,11, 'v')))
        assertEquals(Position(0,5,'>'), navigator.teleport6(Position(14,11, 'v')))
        assertEquals(Position(0,4,'>'), navigator.teleport6(Position(15,11, 'v')))

        //    'Right' -> Right of 1, facing left
        assertEquals(Position(11,3,'<'), navigator.teleport6(Position(15,8, '>')))
        assertEquals(Position(11,2,'<'), navigator.teleport6(Position(15,9, '>')))
        assertEquals(Position(11,1,'<'), navigator.teleport6(Position(15,10, '>')))
        assertEquals(Position(11,0,'<'), navigator.teleport6(Position(15,11, '>')))

    }

    @Test
    fun testPart2(){
        val navigator = Day22.Navigator.fromList(Util().readData("day22-2.txt"), false)
        navigator.setCube2()
        navigator.processAllInstructions();
        assertEquals(189097, navigator.getPassword())
    }
}
