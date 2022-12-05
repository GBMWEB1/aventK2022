import java.io.File

class Util {

    fun readAsInts (file: String): List<Int> {
        return readData(file).map { stringVal -> stringVal.toInt()}
    }

    fun readData(file: String): List<String> {
       return File("src/test/resources/${file}").readText().lines();
    }

//    export const numberArray = (data: string) : number[] =>{
//        return data.split('').map( num => parseInt(num));
//    }
//
//    export const toNumber = (data: string[]) : number[][] =>{
//        return data.map (row => numberArray(row));
//    }
}