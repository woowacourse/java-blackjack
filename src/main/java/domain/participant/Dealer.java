package domain.participant;

import domain.Money;
import domain.card.Score;
import java.util.Collection;

public final class Dealer implements Role {

  private static final String DEFAULT_NAME = DealerRoster.DEFAULT.getName();
  private final Money money;

  public Dealer(final Money money) {
    this.money = money;
  }

  public static Dealer generateFrom(final Collection<Money> values) {
    final int total = values.stream()
        .mapToInt(Money::getValue)
        .sum();
    final Money money = new Money(total);
    return new Dealer(money);
  }

  @Override
  public boolean isHit(final Score score) {
    return score.isDealerNeedHit();
  }

  @Override
  public Money getMoney() {
    return money;
  }

  @Override
  public String getName() {
    return DEFAULT_NAME;
  }

}
