package domain;

import domain.card.Score;
import domain.participant.Participant;
import domain.participant.Role;
import java.util.function.Function;

public enum RoundResult {
  WIN("승", bet -> bet),
  LOSE("패", Bet::lose),
  PUSH("무승부", Bet::push),
  BLACKJACK("블랙잭", Bet::blackjack),
  NONE("없음", x -> x);

  private final String text;
  private final Function<Bet, Bet> betting;

  RoundResult(final String text, Function<Bet, Bet> betting) {
    this.text = text;
    this.betting = betting;
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
