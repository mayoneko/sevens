package mayoneko


data class Card(val suitNum: Int, val num: Int) {

    override fun toString(): String {
        val suitString = when (this.suitNum) {
            0 -> "♥"
            1 -> "♦"
            2 -> "♣"
            3 -> "♠"
            else -> this.suitNum.toString()
        }
        val numString = when (this.num) {
            1 -> "A"
            10 -> "0"
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            else -> this.num.toString()
        }

        return "{$suitString-$numString}"
    }

    fun equals(card: Card): Boolean {
        return this.suitNum == card.suitNum && this.num == card.num
    }

    fun outerCards(): List<Card> {
        return when (this.num) {
            in 2..6 -> listOf(Card(this.suitNum, this.num - 1))
            in 8..12 -> listOf(Card(this.suitNum, this.num + 1))
            7 -> listOf(Card(this.suitNum, this.num - 1), Card(this.suitNum, this.num + 1))
            else -> listOf()
        }
    }

    fun innerCards(): List<Card> {
        return when (this.num) {
            in 1..6 -> listOf(Card(this.suitNum, this.num + 1))
            in 8..13 -> listOf(Card(this.suitNum, this.num - 1))
            else -> listOf()
        }
    }
}