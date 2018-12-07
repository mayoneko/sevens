package mayoneko


data class Card(val suitNum: Int, val num: Int)


class Suit {
    companion object {
        const val HEARTS = 0
        const val DIAMONDS = 1
        const val CLUBS = 2
        const val SPADES = 3
    }
}

fun Card.toString(): String {
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

    return "{$suitString,$numString}"
}

fun Card.toInt(): Int {
    return this.suitNum * 13 + (this.num - 1)
}

fun Int.toCard(): Card{
    return Card(this%4,this%13+1)
}

fun List<Card>.toString() {
    var fullBoard = ""
    var index = 0
    for (i in 0..51) {
        val card = this[index]
        if (i == card.toInt()) {
            fullBoard += card.toString()
            if (index < this.size) {
                index++
            }
        } else {
            fullBoard += "{-:-}"
        }
        if (i % 13 == 12) {
            fullBoard += "\n"
        }
    }
}