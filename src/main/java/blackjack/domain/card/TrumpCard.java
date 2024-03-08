package blackjack.domain.card;

import java.util.List;
import java.util.Objects;

public class TrumpCard implements Card {

    private final Rank rank;
    private final Suit suit;

    public TrumpCard(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public List<Integer> getScore() {
        return rank.getScore();
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
