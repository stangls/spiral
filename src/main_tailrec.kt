import java.lang.Math.*

fun main(args: Array<String>) {
    printSpiralNoCallstack( 10 )
}

fun printSpiralNoCallstack(size: Int) {
    val fieldLen = floor(log10(max(size*size-1,1).toDouble())).toInt()+1
    val evenSizeAdd = if (size % 2 == 0) 1 else 0
    for (line in 0 until size) {
        println (
            getSpiralLineNoCallstack(size+evenSizeAdd,line)
                .drop(evenSizeAdd)
                .map {
                    val s = it.toString()
                    " ".repeat(fieldLen-s.length )+s
                }
                .joinToString(" ")
        )
    }
}

fun getSpiralLineNoCallstack(size: Int, line: Int) : List<Int>
    = getSpiralLine(size,line,listOf(),listOf())

tailrec fun getSpiralLine(size: Int, line: Int, prefix:List<Int>, postfix:List<Int>) : List<Int> =
    if (size==1){
        prefix + 0 + postfix
    } else {
        val max = size * size
        val leftBottom = max - size
        when (line) {
            0 -> {
                val leftTop = leftBottom - size + 1
                val rightTop = leftTop - size + 1
                prefix + (leftTop downTo rightTop) + postfix
            }
            size - 1 -> {
                prefix + (leftBottom until max).toList() + postfix
            }
            else -> {
                val innerSize = size - 2;
                getSpiralLine(innerSize, line - 1,
                    prefix + listOf(leftBottom - (size - line - 1)),
                    listOf(innerSize * innerSize - 1 + size - line - 1) + postfix
                )
            }
        }
    }
