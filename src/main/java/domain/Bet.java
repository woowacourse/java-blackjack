package domain;

import domain.participant.Role;
import exceptions.BlackjackArgumentException;
import java.util.List;
import java.util.Objects;

public final class Bet {

  private static final Bet BET_FOR_LOSE = new Bet(0);

  private final int value;

  public Bet(final int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public Bet lose() {
    return BET_FOR_LOSE;
  }

  public Bet push() {
    return this;
  }

  public Bet blackjack() {
    return new Bet((int) (getValue() * 2.5));
  }

  public Bet win() {
    return new Bet(getValue() * 2);
  }

  public Bet minus(Bet allocated) {
    return new Bet(value - allocated.getValue());
  }

  public Bet seekAllocationTotalDifference(final List<Role> calculated) {
    final var calculatedTotal = calculatedTotal(calculated);
    return this.minus(calculatedTotal);
  }

  private Bet calculatedTotal(final List<Role> calculated) {
    return calculated.stream()
        .map(Role::getBet)
        .reduce(Bet::plus)
        .orElseThrow(() -> new BlackjackArgumentException("배팅 정보가 없습니다."));
  }

  private Bet plus(Bet allocated) {
    return new Bet(value + allocated.getValue());
  }

  @Override
  public String toString() {
    return value + "";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Bet bet = (Bet) o;
    return value == bet.value;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }
}
