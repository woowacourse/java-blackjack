package domain.participant;

import domain.Money;
import domain.card.Score;
import java.util.Map.Entry;

public final class Player implements Role {

  private final String name;
  private final Money money;

  public Player(final String name, Money money) {
    this.name = name;
    this.money = money;
  }

  public static Player generateFrom(Entry<String, Money> entry) {
    return new Player(entry.getKey(), entry.getValue());
  }

  @Override
  public boolean isHit(final Score score) {
    return !score.isBust();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Money getMoney() {
    return money;
  }

}
