package domain.card;

import java.util.Objects;

public class Card {

    private final CardShape cardShape;
    private final CardValue cardValue;

    public Card(final CardShape cardShape, final CardValue cardValue) {
        this.cardShape = cardShape;
        this.cardValue = cardValue;
    }

    public boolean isAce() {
        return cardValue.isAce();
    }

    public CardShape cardShape() {
        return cardShape;
    }

    public CardValue cardValue() {
        return cardValue;
    }

    public int defaultScore() {
        return cardValue.value();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        final Card card = (Card) o;
        return cardShape == card.cardShape && cardValue == card.cardValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardShape, cardValue);
    }
}
