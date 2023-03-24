package blackjack.domain.card;

import blackjack.domain.game.Score;

import java.util.*;

public class Card {

    private static final Map<String, Card> cards = new LinkedHashMap<>();

    static {
        for (final Shape shape : Shape.values()) {
            for (final Denomination denomination : Denomination.values()) {
                cards.put(toKey(shape, denomination), new Card(shape, denomination));
            }
        }
    }

    private final Shape shape;
    private final Denomination denomination;

    private Card(final Shape shape, final Denomination denomination) {
        this.shape = shape;
        this.denomination = denomination;

    }

    private static String toKey(final Shape shape, final Denomination denomination) {
        return shape.name() + denomination.name();
    }

    public static Card from(final Shape shape, final Denomination denomination) {
        return cards.computeIfAbsent(toKey(shape, denomination), ignored -> new Card(shape, denomination));
    }

    public static List<Card> getCards() {
        return new ArrayList<>(cards.values());
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
        return shape == card.shape && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, denomination);
    }

    @Override
    public String toString() {
        return "Card{" +
                "shape=" + shape +
                ", denomination=" + denomination +
                '}';
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Shape getShape() {
        return shape;
    }

    public Score getScore() {
        return new Score(denomination.getScore());
    }

}
