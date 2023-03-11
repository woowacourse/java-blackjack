package domain.card;

import java.util.Objects;

public class Card {

    private final Shape shape;
    private final CardInfo cardInfo;

    public Card(final Shape shape, final CardInfo info) {
        this.shape = shape;
        this.cardInfo = info;
    }

    public boolean isACE() {
        return this.cardInfo.isACE();
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
        return shape == card.shape && cardInfo == card.cardInfo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, cardInfo);
    }

    public String getName() {
        return cardInfo.getName() + shape.getShape();
    }

    public int getValue() {
        return cardInfo.getValue();
    }
}
