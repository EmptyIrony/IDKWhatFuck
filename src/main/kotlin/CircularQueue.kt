class CircularQueue<T>(
    val size: Int
) {
    lateinit var arr: Array<Any?>
    var startPoint = 0
    var endPoint = 0


    fun initQueue() {
        arr = arrayOfNulls(size)
        startPoint = 0
        endPoint = 0
    }

    fun destroyQueue() {
        arr = arrayOfNulls(size)
    }

    /**
     * 向队列中添加一个元素
     * @param element 要添加的元素
     * @return 如果添加成功，返回true；如果队列已满，返回false
     */
    fun enqueue(element: T): Boolean {
        if (isFull()) {
            return false
        }
        arr[endPoint] = element
        endPoint = (endPoint + 1) % size
        return true
    }

    /**
     * 从队列中移除一个元素
     * @return 如果队列为空，返回null；否则返回被移除的元素
     */
    fun dequeue(): T? {
        if (isEmpty()) {
            return null
        }
        val element = arr[startPoint] as T
        arr[startPoint] = null
        startPoint = (startPoint + 1) % arr.size
        return element
    }

    /**
     * 检查队列是否为空
     * @return 如果队列为空，返回true；否则返回false
     */
    fun isEmpty(): Boolean {
        return startPoint == endPoint
    }

    /**
     * 检查队列是否已满
     * @return 如果队列已满，返回true；否则返回false
     */
    fun isFull(): Boolean {
        return (endPoint + 1) % size == startPoint
    }

    /**
     * 获取队列中的元素数量
     * @return 当前队列中的元素数量
     */
    fun size(): Int {
        return if (endPoint >= startPoint) {
            endPoint - startPoint
        } else {
            size - startPoint + endPoint
        }
    }
}

fun main() {
    val queue = CircularQueue<Char>(5)
    println("环形队列基本运算: \n" +
            "  (1)初始化队列")
    queue.initQueue()
    println("  (2)依次进队列元素a, b, c")
    arrayOf('a', 'b', 'c').forEach {
        if (!queue.enqueue(it)) {
            println("提示: 队满, 不能进队列")
        }
    }

    queue.dequeue()?.apply {
        println("  (4) 出队一个元素 $this")
    } ?: println("队空, 不能出队")

    println("  (5)依次进队列元素d, e, f")
    arrayOf('d', 'e', 'f').forEach {
        if (!queue.enqueue(it)) {
            println("提示: 队满, 不能进队列")
        }
    }

    print("  (6)出队列序列")
    while (!queue.isEmpty()) {
        queue.dequeue()?.apply {
            print("$this ")
        }
    }
    println()
    println("  (7)释放队列")
    queue.destroyQueue()
}
