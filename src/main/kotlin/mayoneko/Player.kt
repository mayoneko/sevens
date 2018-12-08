package mayoneko

class Player(_playerID: Int) {
    val cards = mutableListOf<Card>()
    val playerID = _playerID

    companion object {
        const val PLAYING = 0
        const val LOSE = 1
        const val WIN = 2
    }

    private var state = PLAYING
    var remainingPassCount: Int = 3
    var canPass: Boolean = true

    fun pass() {
        //TODO
    }

    fun play(suitNum: Int, num: Int) {
        //TODO
    }

    fun playingAlgorithm() {
        //TODO
    }

    fun searchCards(suitNum: Int? = null, num: Int? = null): List<Card> {
        return when {
            suitNum is Int && num is Int -> cards.filter {
                it.suitNum == suitNum && it.num == num
            }
            suitNum is Int -> cards.filter {
                it.suitNum == suitNum
            }
            num is Int -> cards.filter{
                it.num == num
            }
            else -> cards
        }
    }
}