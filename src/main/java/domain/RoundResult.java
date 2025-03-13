package domain;

import domain.card.Score;
import domain.participant.Participant;
import domain.participant.Role;

public enum RoundResult {
  WIN("승"),
  LOSE("패"),
  PUSH("무승부"),
  BLACKJACK("블랙잭"),
  NONE("없음");

  private final String text;

  RoundResult(final String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

  public static RoundResult round(
      final Participant<? extends Role> player,
      final Participant<? extends Role> dealer
  ) {
    final var score = player.calculateScore();
    final var dealerScore = dealer.calculateScore();

    if (score.isBust()) {
      return RoundResult.LOSE;
    }
    if (dealerScore.isBust() || score.isGreaterThan(dealerScore)) {
      return RoundResult.WIN;
    }
    return getRoundResult(player, dealerScore, score);
  }

  private static RoundResult getRoundResult(
      final Participant<? extends Role> player,
      final Score dealerScore,
      final Score score
  ) {

    if (score.equals(dealerScore)) {
      return RoundResult.PUSH;
    }
    if (player.isBlackjack()) {
      return RoundResult.BLACKJACK;
    }
    return RoundResult.LOSE;
  }

}
