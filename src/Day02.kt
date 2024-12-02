import kotlin.math.abs

fun main() {
    fun getSafeInput(makeSafe: Boolean = false): Int {
        return readInput("Day02").map {
            it.split(" ").map { it.toInt() }
        }.map {
            var isSafe = true
            var hasResolved = false
            val list = it.toMutableList()
            if (it.contains(0).not()) {
                var isIncreasing = false
                var isDecreasing = false
                if (list[1] > list[0]) {
                    isIncreasing = true
                } else isDecreasing = true

                for (index in list.indices) {
                    if (index > 0) {
                        if (isIncreasing && hasResolved.not()) {
                            if (list[index] <= list[index - 1]) {
                                if (makeSafe) {
                                    list.removeAt(index - 1)
                                    hasResolved = true
                                } else {
                                    isSafe = false
                                    break
                                }
                            }
                        }
                        if (isDecreasing && hasResolved.not()) {
                            if (list[index] >= list[index - 1]) {
                                if (makeSafe) {
                                    list.removeAt(index- 1)
                                    hasResolved = true
                                } else {
                                    isSafe = false
                                    break
                                }
                            }
                        }
                    }
                }
                val increments = buildList {
                    list.forEachIndexed { index, s ->
                        if (index > 0 && index < list.lastIndex) {
                            add(abs(s - list[index - 1]))
                        }
                    }
                }
                if (isSafe) {
                    for (i in increments) {
                        when (i) {
                            1, 2, 3 -> {
                                isSafe = true
                            }

                            else -> {
                                isSafe = false
                                break
                            }
                        }
                    }
                }
            }
            isSafe
        }.filter { it }.count()
    }
    fun part1(): Int {
       return  getSafeInput()
    }

    fun part2(): Int {
        return  getSafeInput(makeSafe = true)
    }

    print("Number of safe reports: ")
    part1().println()

    print("Number of safe reports: ")
    part2().println()
}