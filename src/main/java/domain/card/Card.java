package domain.card;

import java.util.Objects;

public class Card {
    private final Denomination denomination;
    private final Suit suit;

    public Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public String getDenominationExpression() {
        return this.denomination.getExpression();
    }

    public String getSuitName() {
        return this.suit.getName();
    }

    boolean isAce() {
        return denomination.isAce();
    }

    int getNumber() {
        return denomination.getNumber();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Card other) {
            return this.denomination == other.denomination && this.suit == other.suit;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, suit);
    }
}
