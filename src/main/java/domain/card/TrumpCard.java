package domain.card;

import constant.Suit;
import java.util.Objects;

public record TrumpCard(
    Rank rank,
    Suit suit
) {

  public boolean isMatchRank(final Rank other) {
    return Objects.equals(rank, other);
  }

  public int add(final int score) {
    return rank.add(score);
  }
}
