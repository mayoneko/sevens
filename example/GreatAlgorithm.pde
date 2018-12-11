import sevens.Algorithm.Utils;

class GreatAlgorithm extends Algorithm {

  Card choiceCard(List board, List hand, int remainingPassCount, List otherPlayersStatus) {

    // Make your greatest algorithm!

    List<Card> playableHand = Utils.getPlayableHand(board, hand);
    if (playableHand.isEmpty()) {
      return Utils.pass();
    } else {
      return Utils.play(playableHand.get((int)random(playableHand.size())));
    }

  }

}
