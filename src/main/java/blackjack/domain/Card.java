package blackjack.domain;

import java.util.Objects;

public class Card {
    private static final CardNumber TEN = CardNumber.of(10);

    private final Shape shape;
    private final CardNumber cardNumber;

    public Card(final Shape shape, final CardNumber cardNumber) {
        this.shape = shape;
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return shape == card.shape && Objects.equals(cardNumber, card.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, cardNumber);
    }

    public boolean isAce() {
        return cardNumber == CardNumber.of(1);
    }

    public boolean isOverTen() {
        return TEN.compareTo(cardNumber) <= 0;
    }

    public int getCardNumberValue() {
        return cardNumber.getValue();
    }
}
