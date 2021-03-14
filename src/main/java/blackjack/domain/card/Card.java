package blackjack.domain.card;

import java.util.Objects;

public final class Card {

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getRankInitial() {
        return rank.getInitial();
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public int getUpperValue() {
        if (isAce()) {
            return Rank.ACE_UPPER_VALUE;
        }
        return rank.getValue();
    }

    public String getSuitName() {
        return suit.getName();
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}
