package domain;

import constant.Rank;
import constant.Suit;
import java.util.Objects;

public class Card {

    private final Rank Rank;
    private final Suit suit;

    public Card(constant.Rank rank, Suit suit) {
        Rank = rank;
        this.suit = suit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return Rank == card.Rank && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Rank, suit);
    }
}
