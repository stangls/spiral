import java.lang.Math.*

fun main(args: Array<String>) {
    printSpiralDirectly( 10 )
}

fun printSpiralDirectly(size: Int) {
    val fieldLen = floor(log10(max(size*size-1,1).toDouble())).toInt()+1
    val evenSizeAdd = if (size % 2 == 0) 1 else 0
    for (line in 0 until size) {
        printSpiralLine(size+evenSizeAdd,line,evenSizeAdd,fieldLen)
        println()
    }
}

fun printSpiralLine(size: Int, line: Int, dropFirstNumbers: Int, fieldLen:Int) {
    fun printNum(num:Int){
        val s = num.toString()
        print(" ".repeat(fieldLen-s.length+1 )+s)
    }
    if (size == 1) {
        printNum(0)
    } else {
        val max = size * size
        val leftBottom = max - size
        when (line) {
            0 -> {
                val leftTop = leftBottom - size + 1
                val rightTop = leftTop - size + 1
                for (num in leftTop - dropFirstNumbers downTo rightTop) {
                    printNum(num)
                }
            }
            size - 1 -> {
                for (num in leftBottom + dropFirstNumbers until max) {
                    printNum(num)
                }
            }
            else -> {
                val innerSize = size - 2;
                if (dropFirstNumbers==0)
                    printNum(leftBottom - (size - line - 1))
                printSpiralLine(innerSize, line - 1,0,fieldLen)
                printNum(innerSize * innerSize - 1 + size - line - 1)
            }
        }
    }
}