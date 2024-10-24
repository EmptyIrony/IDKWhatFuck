import java.util.Scanner
import kotlin.system.exitProcess

val queue = LinkedQueue<Int>()

fun main() {
    while (true) {
        printMenu()
        val line = readlnOrNull() ?: run {
            println("退出.")
            return
        }
        val operatorNumber = line.toIntOrNull()
        if (operatorNumber == null) {
            println("输入的不是有效数字")
            continue
        }
        handleInput(operatorNumber)
    }
}

private fun printMenu() {
    println("菜单: ")
    println("  1). 排队")
    println("  2). 就诊")
    println("  3). 查看排队")
    println("  4). 不在排队, 余下依次就诊")
    println("  5). 下班")
    println("输入数字以操作")
}

private fun handleInput(input: Int) {
    when(input) {
        1 -> {
            println("输入病历号: ")
            val number = readlnOrNull()?.toIntOrNull() ?: run {
                println("非数字")
                return
            }

            queue.enqueue(number)
        }
        2 -> {
            val next = queue.dequeue() ?: run {
                println("当前没有病人就诊")
                return
            }

            println("请 $next 病人就诊")
        }
        3 -> {
            println("排队就诊列表: ${queue.toList().joinToString(", ")}")
        }
        4 -> {
            println("依次就诊列表: ${queue.toList().joinToString(", ")}")

            println("退出.")
            exitProcess(0)
        }
        5 -> {
            println("退出.")
            exitProcess(0)
        }
    }
}