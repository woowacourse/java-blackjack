package blackjack.domain.card;

import java.util.Objects;

public record Card(Suit suit, Rank rank) {

    public String toDisplayName() {
        return rank.getName() + suit.getDisplayName();
    }

    public int getValue() {
        return rank.getValue();
    }

    public boolean isAce() {
        return rank.isAce();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit() == card.suit() && rank() == card.rank();
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit(), rank());
    }
}
