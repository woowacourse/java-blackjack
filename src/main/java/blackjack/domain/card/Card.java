package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class Card {
    private static final List<Card> CARDS;

    private final Number number;
    private final Shape shape;

    static {
        CARDS = Arrays.stream(Number.values())
                .flatMap(Card::createAllShapeCardsForNumber)
                .toList();
    }

    private static Stream<Card> createAllShapeCardsForNumber(Number number) {
        return Arrays.stream(Shape.values())
                .map(shape -> new Card(number, shape));
    }

    private Card(Number number, Shape shape) {
        this.number = number;
        this.shape = shape;
    }

    public static Card from(Number number, Shape shape) {
        return CARDS.stream()
                .filter(card -> card.number == number && card.shape == shape)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public boolean isAce() {
        return number.equals(Number.ACE);
    }

    public String getSignature() {
        return number.getSymbol() + shape.getValue();
    }

    public int getScore() {
        return number.getScore();
    }
}
