package domain;

import java.util.Objects;

public class Bet {

  private final int value;

  public Bet(final int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public Bet lose() {
    return new Bet(0);
  }

  public Bet push() {
    return this;
  }

  public Bet blackjack() {
    return new Bet((int) (getValue() * 1.5));
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
