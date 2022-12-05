package old

import Util
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    fun testAsciiConversion() {
        assertEquals(1, 'a'.asciiValue())
        assertEquals(26, 'z'.asciiValue())
        assertEquals(27, 'A'.asciiValue())
        assertEquals(52, 'Z'.asciiValue())
    }

    @Test
    fun testSpitRucksack() {
        assertEquals(listOf("vJrwpWtwJgWr", "hcsFMMfFFhFp"), Day3().splitRucksack("vJrwpWtwJgWrhcsFMMfFFhFp"))
    }

    @Test
    fun testCompareCompartments() {
        assertEquals('p', Day3().compareCompartments("vJrwpWtwJgWr", "hcsFMMfFFhFp"))
    }

    @Test
    fun testGetPriorityItemValue() {
        assertEquals(16, Day3().getPriorityItemValue("vJrwpWtwJgWrhcsFMMfFFhFp"))
    }

    @Test
    fun testGetTotalPriorities() {
        val rucksacks = listOf(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw"
        )
        assertEquals(157, Day3().getTotalPriorities(rucksacks))
    }

    @Test
    fun testCh1() {
        val rucksacks = Util().readData("day3-1.txt")
        assertEquals(7850, Day3().getTotalPriorities(rucksacks))
    }

    @Test
    fun testCommonItemType() {
        val rucksacks = listOf(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg"
        )
        assertEquals('r', Day3().getBadge(rucksacks))
    }

    @Test
    fun testCommonItemTypeValue() {
        val rucksacks = listOf(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg"
        )
        assertEquals(18, Day3().getBadge(rucksacks).asciiValue())
    }

    @Test
    fun testBadgesValue() {
        val rucksacks = listOf(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw"
        )
        assertEquals(70, Day3().getBadgesValue(rucksacks))
    }

    @Test
    fun testCh2() {
        val rucksacks = Util().readData("day3-1.txt")
        assertEquals(2581, Day3().getBadgesValue(rucksacks))
    }

    @Test
    fun testListChunk() {
        val rucksacks = listOf(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw"
        )
        val chuncked = listOf(
            listOf(
                "vJrwpWtwJgWrhcsFMMfFFhFp",
                "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
                "PmmdzqPrVvPwwTWBwg"
            ),
            listOf(
                "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
                "ttgJtRGJQctTZtZT",
                "CrZsJsPPZsGzwwsLwLmpwMDw"
            )
        )
        assertEquals(chuncked, rucksacks.chunk(3))
    }
}
