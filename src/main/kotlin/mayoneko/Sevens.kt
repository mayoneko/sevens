package mayoneko


fun main(args: Array<String>) {
    val algorithms = listOf(RandomAlgorithm(), KidsAlgorithm(), MayonekoAlgorithm())
    val winingRates = (0 until algorithms.size).map { 0 }.toMutableList()
    for (i in 0 until 10000) {
        val result = Game(algorithms).run()
        val winner = result[1] as Int
        winingRates[winner]++
    }
    println(winingRates)
}