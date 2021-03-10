package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Card {
    private static final List<Card> CACHE;

    static {
        CACHE = Arrays.stream(Denomination.values())
                .flatMap(denomination -> Arrays.stream(Shape.values())
                        .map(shape -> new Card(shape, denomination)))
                .collect(Collectors.toList());
    }

    private final Shape shape;
    private final Denomination denomination;

    public Card(Shape shape, Denomination denomination) {
        this.shape = shape;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return this.denomination.isAce();
    }

    public int addScore(int score) {
        return denomination.addScore(score);
    }

    public static List<Card> values() {
        return Collections.unmodifiableList(CACHE);
    }

    public Denomination getDenomination() {
        return this.denomination;
    }

    public Shape getShape() {
        return this.shape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return shape == card.shape && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, denomination);
    }
}
