package domain.card;

import java.util.Objects;

public class Card {
    public static final int BUST_DEADLINE = 21;

    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    public int getScore() {
        return denomination.getScore();
    }

    public String getNumberDescription() {
        return denomination.getDescription();
    }

    public String getShapeDescription() {
        return suit.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, denomination);
    }

    @Override
    public String toString() {
        return "Card{" +
                "shape=" + suit +
                ", number=" + denomination +
                '}';
    }
}
