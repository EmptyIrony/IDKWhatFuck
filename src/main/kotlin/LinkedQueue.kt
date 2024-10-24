class LinkedQueue<T> {

    var firstNode: Node<T>? = null

    var lastNode: Node<T>? = null

    fun initQueue() {

    }

    fun destroy() {
        firstNode = null
        lastNode = null
    }

    fun empty(): Boolean {
        return firstNode == null
    }

    fun enqueue(element: T) {
        val firstNode = firstNode
        if (firstNode == null) {
            this.firstNode = Node(element, null)
            lastNode = this.firstNode
        } else {
            // 修改末节点
            lastNode = Node(
                element, null
            ).apply {
                // 上一个末节点指向这个节点
                lastNode!!.nextNode = this
            }
        }
    }

    fun dequeue(): T? {
        val node = firstNode ?: return null
        firstNode = node.nextNode

        return node.element
    }

    fun toList(): List<T> {
        var node = firstNode
        val list = ArrayList<T>()

        while (node != null) {
            list += node.element
            node = node.nextNode
        }

        return list
    }

    class Node<T>(
        val element: T,
        var nextNode: Node<T>?,
    )
}

fun main() {
    val queue = LinkedQueue<Char>()
    println("链式队列基本运算: \n" +
            "  (1)初始化队列")
    queue.initQueue()
    println("  (2)依次进队列元素a, b, c")
    arrayOf('a', 'b', 'c').forEach {
        queue.enqueue(it)
    }

    queue.dequeue()?.apply {
        println("  (4) 出队一个元素 $this")
    } ?: println("队空, 不能出队")

    println("  (5)依次进队列元素d, e, f")
    arrayOf('d', 'e', 'f').forEach {
        queue.enqueue(it)
    }

    print("  (6)出队列序列")
    while (!queue.empty()) {
        queue.dequeue()?.apply {
            print("$this ")
        }
    }
    println()
    println("  (7)释放队列")
    queue.destroy()
}