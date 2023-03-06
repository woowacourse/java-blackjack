package blackjack.domain.card;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class Card {
    private static final Map<Suit, Map<CardNumber, Card>> CARDS;
    private final Suit shape;
    private final CardNumber number;

    static {
        CARDS = Arrays.stream(CardNumber.values())
                .flatMap(cardNumber -> Arrays.stream(Suit.values())
                        .map(suit -> new Card(suit, cardNumber)))
                .collect(groupingBy(card -> card.shape,
                        toMap(card -> card.number, card -> card)));
    }

    private Card(final Suit suit, final CardNumber number) {
        this.shape = suit;
        this.number = number;
    }

    public static Card of(final Suit suit, final CardNumber number) {
        return CARDS.get(suit).get(number);
    }

    public CardNumber getNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        final Card card = (Card) o;
        return shape == card.shape && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, number);
    }

    @Override
    public String toString() {
        return number.getNumber() + shape.getValue();
    }
}
