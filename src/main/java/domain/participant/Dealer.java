package domain.participant;

import domain.Bet;
import domain.card.Score;
import java.util.Collection;

public final class Dealer implements Role {

  private static final String DEFAULT_NAME = DealerRoster.DEFAULT.getName();
  private final Bet bet;

  public Dealer(final Bet bet) {
    this.bet = bet;
  }

  public static Dealer generateByTotalBet(final Collection<Bet> values) {
    final int total = values.stream()
        .mapToInt(Bet::getValue)
        .sum();
    final Bet bet = new Bet(total);
    return new Dealer(bet);
  }

  @Override
  public boolean isHit(final Score score) {
    return score.isNeedHitByDealer();
  }

  @Override
  public Bet getBet() {
    return bet;
  }

  @Override
  public String getName() {
    return DEFAULT_NAME;
  }

}
