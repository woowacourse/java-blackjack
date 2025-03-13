package domain.participant;

import domain.Bet;
import domain.card.Score;
import java.util.Map.Entry;

public final class Player implements Role {

  private final String name;
  private final Bet bet;

  public Player(final String name, final Bet bet) {
    this.name = name;
    this.bet = bet;
  }

  public static Player generateFrom(final Entry<String, Bet> entry) {
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
  public Bet getBet() {
    return bet;
  }

}
