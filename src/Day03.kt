fun main() {
    fun part1(): Int {
        val input = readInputText("Day03")
        val matcher = Regex(pattern = "mul\\([0-9]+,[0-9]+\\)", options = setOf(RegexOption.IGNORE_CASE))
        return matcher.findAll(input).map{ it.value
            .replace("mul", "")
            .replace("(", "")
            .replace(")", "")
        }.toList().map {
            val values = it.split(",")
            values.first().toInt() * values.last().toInt()
        }.reduce { x, y -> x + y }
    }

    fun part2(): Int {
        val total = part1()
        val input = readInputText("Day03")
        val dontValues = Regex(pattern = "do_not_.*mul\\\\([0-9]+,[0-9]+\\\\)", options = setOf(RegexOption.IGNORE_CASE)).findAll(input).map {
            it.value
        }.toList().map {
            val values = it.split(",")
            values.first().toInt() * values.last().toInt()
        }.reduceOrNull { x, y -> x + y } ?: 0

        val undoValues = Regex(pattern = "mul\\([0-9]+,[0-9]+\\)undo\\(\\)", options = setOf(RegexOption.IGNORE_CASE)).findAll(input).map {
            it.value
                .replace("mul", "")
                .replace("(", "")
                .replace(")undo)", "")
        }.toList().map {
            val values = it.split(",")
            values.first().toInt() * values.last().toInt()
        }.reduceOrNull { x, y -> x + y } ?: 0

        return total - (dontValues + undoValues)
    }

    part1().println()
    part2().println()
}