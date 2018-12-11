package sevens

import sevens.Algorithm.Utils.getPlayableHand
import sevens.Algorithm.Utils.pass
import sevens.Algorithm.Utils.play
import kotlin.math.absoluteValue

class KidsAlgorithm : Algorithm() {
    override fun choiceCard(
        board: List<Card>,
        hand: List<Card>,
        remainingPassCount: Int,
        otherPlayersStatus: List<Player.Status>
    ): Card? {
        val playableHand = getPlayableHand(board, hand)
        if (playableHand.isEmpty()) {
            return pass()
        } else {
            val cardWillPlay = playableHand.sortedByDescending { card ->
                (7 - card.number).absoluteValue
            }[0]
            return play(cardWillPlay)
        }
    }
}

class MayonekoAlgorithm : Algorithm() {
    override fun choiceCard(
        board: List<Card>,
        hand: List<Card>,
        remainingPassCount: Int,
        otherPlayersStatus: List<Player.Status>
    ): Card? {
        val playableHand = getPlayableHand(board, hand)
        if (playableHand.isEmpty()) {
            return pass()
        } else {
            val playableAorK = playableHand.filter { card -> card.number == 1 || card.number == 13 }
            if (playableAorK.isNotEmpty()) {
                return play(playableAorK[0])
            }
            val playableHandLastingAorK = playableHand.filter { card ->
                val searchNums = if (card.number > 7) (card.number..13) else (1..card.number)
                searchNums.all { num ->
                    hand.any { it.suit == card.suit && it.number == num }
                }
            }
            if (playableHandLastingAorK.isNotEmpty()) {
                return play(playableHandLastingAorK[0])
            }
            val sortedHandFromAorK = hand.sortedByDescending { card ->
                (7 - card.number).absoluteValue
            }
            val playableHandWithFarHand = playableHand.sortedBy { card ->
                (7 - card.number).absoluteValue
            }.associate { card ->
                val searchNums = if (card.number > 7) (13 downTo card.number).toList() else (1..card.number).toList()
                var length = 0
                for (num in searchNums) {
                    if (sortedHandFromAorK.any { it.number == num && it.suit == card.suit }) {
                        length = (num - card.number).absoluteValue
                        break
                    }
                }
                card to length
            }
            val cardWillPlay =
                playableHandWithFarHand.filter { it.value == playableHandWithFarHand.values.max() }.toList()[0].first
            return play(cardWillPlay)
        }
    }
}

class RandomAlgorithm : Algorithm() {
    override fun choiceCard(
        board: List<Card>,
        hand: List<Card>,
        remainingPassCount: Int,
        otherPlayersStatus: List<Player.Status>
    ): Card? {
        val playableHand = getPlayableHand(board, hand)
        if (playableHand.isEmpty()) {
            return pass()
        } else {
            val cardWillPlay = playableHand.random()
            return play(cardWillPlay)
        }
    }
}