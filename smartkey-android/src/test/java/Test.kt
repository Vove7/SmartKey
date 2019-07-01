import cn.vove7.smartkey.tool.Vog
import org.jetbrains.annotations.TestOnly
import java.lang.Thread.sleep
import java.util.*

/**
 *
 */


fun main() {

    val b = System.currentTimeMillis()

    Vog.init(100)

    val start = Runtime.getRuntime().freeMemory()
    println(Arrays.toString(RunConfig.intArray))
    val start2 = Runtime.getRuntime().freeMemory()
    println("mem: ${(start - start2) shr 10}")

    println(RunConfig.number)

    RunConfig.number = 2
    println(RunConfig.string)
    RunConfig.string = "aaa"
    println(RunConfig.nullableNumber)
    RunConfig.nullableNumber = 5

    println(RunConfig.nullableString)
    RunConfig.nullableString = "abc"

    println(RunConfig.model)
    RunConfig.model = Model()
    println(RunConfig.model)

    RunConfig.intArray = intArrayOf(2, 3, 4, 9)

    val e = System.currentTimeMillis()
    println(e - b)

}