package domain;

import domain.card.Score;
import domain.participant.Participant;
import domain.participant.Role;
import java.util.function.Function;

public enum RoundResult {
  WIN(Bet::win),
  LOSE(Bet::lose),
  PUSH(Bet::push),
  BLACKJACK(Bet::blackjack),
  NONE(x -> x);

  private final Function<Bet, Bet> allocation;

  RoundResult(Function<Bet, Bet> allocation) {
    this.allocation = allocation;
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

  public Bet allocate(Bet other) {
    return allocation.apply(other);
  }
}
