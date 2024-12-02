import kotlin.math.abs

fun main() {
    fun part1(): Int {
        val input = readInput("Day01")
        val firstList = mutableListOf<Int>()
        val secondList = mutableListOf<Int>()
        input.forEach {
            firstList.add(it.split(" ").first().trim().toInt())
            secondList.add(it.split(" ").last().trim().toInt())
        }
        firstList.sort()
        secondList.sort()

        return firstList.mapIndexed { index, i ->
            abs(i - secondList[index])
        }.reduce { acc, i -> acc + i }
    }
    fun part2(): Int {
        val input = readInput("Day01")
        val counts = mutableMapOf<Int, Int>()
        val firstList = mutableListOf<Int>()
        val secondList = mutableListOf<Int>()
        input.forEach {
            counts.putIfAbsent(it.split(" ").first().trim().toInt(), 0)
            firstList.add(it.split(" ").first().trim().toInt())
            secondList.add(it.split(" ").last().trim().toInt())
        }
        firstList.forEach { key ->
            secondList.forEach {
                if (counts.containsKey(it) && key == it) {
                    counts[it] = (counts[it] ?: 0) + 1
                }
            }
        }
        return counts.map {
            it.key * it.value
        }.reduce { acc, i ->  acc + i }
    }
    part1().println()
    part2().println()
}
