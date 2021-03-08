package blackjack.domain.card;

import java.util.Arrays;
import java.util.Objects;

public class Card {
    private static final int cardSize = 52;
    private static final int shapeSize = 12;
    private static final Card[] cards = new Card[cardSize];

    private final CardShape shape;
    private final CardNumber number;

    static {
        Arrays.stream(CardShape.values())
            .forEach(shape -> Arrays.stream(CardNumber.values())
                .forEach(number ->
                    cards[shape.getValue() * shapeSize + number.getValue() - 1]
                        = new Card(shape, number)
                ));
    }

    private Card(final CardShape shape, final CardNumber number) {
        this.shape = shape;
        this.number = number;
    }

    public static Card valueOf(final CardShape shape, final CardNumber number) {
        return cards[shape.getValue() * shapeSize + number.getValue() - 1];
    }

    public CardNumber getNumber() {
        return number;
    }

    public int getCardValue() {
        return number.getValue();
    }

    @Override
    public String toString() {
        return number.getNumber() + shape.getShape();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return shape == card.shape && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, number);
    }
}