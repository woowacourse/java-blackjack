package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Card {

    private static final List<Card> CACHE = new ArrayList<>();

    static {
        for (Shape shape : Shape.values()) {
            generateCard(shape);
        }
    }

    private final Rank rank;
    private final Shape shape;

    public Card(final Rank rank, final Shape shape) {
        this.rank = rank;
        this.shape = shape;
    }

    private static void generateCard(final Shape shape) {
        for (Rank rank : Rank.values()) {
            CACHE.add(new Card(rank, shape));
        }
    }

    public static List<Card> values() {
        return List.copyOf(CACHE);
    }

    public boolean isAce() {
        return rank.isAce();
    }

    public int getCardNumber() {
        return rank.getValue();
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Card card)) {
            return false;
        }
        return Objects.equals(rank, card.rank) && Objects.equals(shape, card.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, shape);
    }

    @Override
    public String toString() {
        return rank.getName() + shape.getName();
    }
}
