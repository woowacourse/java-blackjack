package blackjack.domain.card;

import blackjack.domain.Rank;
import blackjack.domain.Suit;
import java.util.List;
import java.util.Objects;

public class Card {

    private final Rank rank;
    private final Suit suit;

    public Card(final Rank rank, final Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSymbol() {
        return suit;
    }

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
        Card card = (Card) o;
        return getRank() == card.getRank() && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRank(), suit);
    }
}
