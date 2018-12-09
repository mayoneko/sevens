package mayoneko


fun main(args: Array<String>) {
    val winingRates = mutableMapOf(Pair(0, 0), Pair(1, 0), Pair(2, 0))
    for (i in 0 until 1000) {
        val result = Game().run()
        val winner = result[1] as Int
        winingRates[winner] = winingRates[winner] as Int + 1
    }
    println(winingRates)
}