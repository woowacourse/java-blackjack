package domain.card;

import java.util.Objects;

public final class TrumpCard {

  private final Suit suit;
  private final Rank rank;

  public TrumpCard(final Rank rank, final Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public Score getScore() {
    return rank.getScore();
  }

  public boolean isAce() {
    return this.rank == Rank.ACE;
  }

  public Rank getRank() {
    return rank;
  }

  public Suit getSuit() {
    return suit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TrumpCard trumpCard = (TrumpCard) o;
    return suit == trumpCard.suit && rank == trumpCard.rank;
  }

  @Override
  public int hashCode() {
    return Objects.hash(suit, rank);
  }
}
