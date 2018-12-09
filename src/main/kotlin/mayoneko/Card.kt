package mayoneko


data class Card constructor(val _suitNum: Int, val _cardNum: Int) {
    val suitNum: Int

    val cardNum: Int

    init {
        if (_suitNum in (0..3)) {
            suitNum = _suitNum
        } else {
            throw IllegalArgumentException("suitNum argument must be in 0..3")
        }
        if (_cardNum in (1..13)) {
            cardNum = _cardNum
        } else {
            throw IllegalArgumentException("cardNum argument must be in 1..13")
        }
    }

    override fun toString(): String {
        val suitString = when (this.suitNum) {
            0 -> "♥"
            1 -> "♦"
            2 -> "♣"
            3 -> "♠"
            else -> throw IllegalStateException("suitNum property must be in 0..3")
        }
        val numString = when (this.cardNum) {
            1 -> "A"
            10 -> "0"
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            in 2..9 -> this.cardNum.toString()
            else -> throw IllegalStateException("cardNum property must be in 1..13")
        }

        return "{$suitString-$numString}"
    }

    fun equals(card: Card): Boolean {
        return this.suitNum == card.suitNum && this.cardNum == card.cardNum
    }

    fun outerCards(): List<Card> {
        return when (this.cardNum) {
            in 2..6 -> listOf(Card(this.suitNum, this.cardNum - 1))
            in 8..12 -> listOf(Card(this.suitNum, this.cardNum + 1))
            7 -> listOf(Card(this.suitNum, this.cardNum - 1), Card(this.suitNum, this.cardNum + 1))
            else -> listOf()
        }
    }

    fun innerCards(): List<Card> {
        return when (this.cardNum) {
            in 1..6 -> listOf(Card(this.suitNum, this.cardNum + 1))
            in 8..13 -> listOf(Card(this.suitNum, this.cardNum - 1))
            else -> listOf()
        }
    }
}