package old

import java.lang.Exception
import java.math.BigInteger

class Day11 {

    data class Operation(val instruction: String){

        fun perform(value: BigInteger): BigInteger {
            if (instruction== "old * old"){
                return value * value
            }
            if (instruction.startsWith("old *")){
                return value * BigInteger(instruction.split(" ")[2])
            }
            if (instruction.startsWith("old +")) {
                return value + BigInteger(instruction.split(" ")[2])
            }
            throw Exception("Unknown Operation $instruction")
        }

        fun performInt(value: Long): Int{
            return perform(BigInteger.valueOf(value)).toInt()
        }

        companion object{
            fun of(line:String): Operation {
                return Operation(line.substring(19))
            }
        }
    }

    class Monkey(itemList: List<BigInteger>,
                 val operation: Operation,
                 val testDiv: BigInteger,
                 val monkeyTrueIndex: Int,
                 val monkeyFalseIndex: Int,
                 private val reduceWorry: Boolean
     ){

        private lateinit var monkeyFalse: Monkey
        private lateinit var monkeyTrue: Monkey

        private var overallMod: Int =0

        private val items = ArrayDeque(itemList.map { it })
        var itemsProcessed = 0

        fun getItemsInt(): List<Int>{
            return items.toList().map { it.toInt() }
        }
        companion object{
            fun of(lines: List<String>, reduceWorry: Boolean): Monkey {
                val items = lines[1]
                    .substring(18)
                    .split(", ")
                    .map { it.toBigInteger() }
                    .toList()

                return Monkey(
                    items,
                    Operation.of(lines[2]),
                    BigInteger(lines[3].substring(21)),
                    lines[4].substring(29).toInt(),
                    lines[5].substring(30).toInt(),
                    reduceWorry
                )
            }
            fun getBored(item: BigInteger):BigInteger {
                return item / BigInteger("3")
            }
        }

        fun init(monkeys: List<Monkey>) {
            monkeyTrue = monkeys[monkeyTrueIndex]
            monkeyFalse = monkeys[monkeyFalseIndex]

            overallMod =
                monkeys.map { it.testDiv.toLong() }.fold(1L) { acc, l -> acc * l }.toInt()
        }

        fun processAllItems(){
            while (items.isNotEmpty()){
                processItem(items.removeFirst())
            }
        }

        private fun processItem(item : BigInteger){
            var item = operation.perform(item)
            if (reduceWorry) {
                item = getBored(item)
            }

            // truncate the value (// as each monkey only cares about its own divisor, trunking here of the product of
            // all divisors ensure that the values are kept small
            item %= overallMod.toBigInteger()

            if ( (item % testDiv).toInt() == 0){
                monkeyTrue.addItem(item)
            } else{
                monkeyFalse.addItem(item)
            }
            itemsProcessed++
        }

        private fun addItem(item: BigInteger) {
            items.add(item )
        }
    }

    fun createMonkeys(input: List<String>, reduceWorry: Boolean = true): List<Monkey>{
        val monkeys = input
            .filter { it.isNotEmpty() }
            .chunk(6)
            .map { Monkey.of(it, reduceWorry) }

        monkeys.forEach { it.init(monkeys) }
        return monkeys
    }
    fun processRound(monkeys : List<Monkey>){
        monkeys.forEach { it.processAllItems() }
    }

    fun processRounds(count: Int, monkeys : List<Monkey>){
        repeat(count){
            processRound(monkeys)
        }
    }

    fun getMonkeyBusiness(monkeys : List<Monkey>): Long{
        return monkeys.map { it.itemsProcessed }
            .sortedDescending()
            .take(2)
            .let { it[0].toLong() * it[1].toLong() }
    }

}