package blackjack.domain.card;

import java.util.Objects;

public class TrumpCard {

    private final Rank rank;
    private final Suit suit;

    public TrumpCard(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isAce() {
        return rank.equals(Rank.ACE);
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getScore() {
        return rank.getScore().get(0);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrumpCard trumpCard = (TrumpCard) o;
        return getRank() == trumpCard.getRank() && getSuit() == trumpCard.getSuit();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRank(), getSuit());
    }
}
