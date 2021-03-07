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
        return this.denomination.equals(Denomination.ACE);
    }

    public int score() {
        return this.denomination.getScore();
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
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (!Objects.equals(shape, card.shape)) return false;
        return Objects.equals(denomination, card.denomination);
    }

    @Override
    public int hashCode() {
        int result = shape != null ? shape.hashCode() : 0;
        result = 31 * result + (denomination != null ? denomination.hashCode() : 0);
        return result;
    }
}
