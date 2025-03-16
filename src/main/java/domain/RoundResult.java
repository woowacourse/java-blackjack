package domain;

import domain.participant.Participant;
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
      final Participant player,
      final Participant dealer
  ) {
    final var playerScore = player.calculateScore();
    final var dealerScore = dealer.calculateScore();

    if (playerScore.isBust()) {
      return RoundResult.LOSE;
    }
    if (playerScore.equals(dealerScore)) {
      return RoundResult.PUSH;
    }
    if (player.isBlackjack()) {
      return RoundResult.BLACKJACK;
    }
    if (dealerScore.isBust() || playerScore.isGreaterThan(dealerScore)) {
      return RoundResult.WIN;
    }
    return RoundResult.LOSE;
  }

  public Bet allocate(Bet other) {
    return allocation.apply(other);
  }
}
