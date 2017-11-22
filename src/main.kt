import java.lang.Math.*

fun main(args: Array<String>) {
    printSpiral( 13 )
}

fun printSpiral(size: Int) {
    val fieldLen = floor(log10(max(size*size-1,1).toDouble())).toInt()+1
    val evenSizeAdd = if (size % 2 == 0) 1 else 0
    for (line in 0 until size) {
        println (
            getSpiralLine(size+evenSizeAdd,line)
                .drop(evenSizeAdd)
                .map {
                    val s = it.toString()
                    " ".repeat(fieldLen-s.length )+s
                }
                .joinToString(" ")
        )
    }
}

fun getSpiralLine(size: Int, line: Int) : List<Int> =
    if (size==1) {
        listOf(0)
    }else{
        val max = size*size
        val leftBottom = max - size
        when (line) {
            0 -> {
                val leftTop = leftBottom - size + 1
                val rightTop = leftTop - size + 1
                (leftTop downTo rightTop).toList()
            }
            size-1 -> {
                (leftBottom until max).toList()
            }
            else -> {
                val innerSize = size-2;
                listOf(leftBottom - (size-line-1)) +
                getSpiralLine(innerSize, line - 1) +
                ( innerSize*innerSize-1 + size-line-1 )
            }
        }
    }
