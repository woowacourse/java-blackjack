package domain.card;

import java.util.*;

public class Card {

    private static final List<Card> CACHE = new ArrayList<>();

    static {
        for (Shape shape : Shape.values()) {
            Arrays.stream(Rank.values())
                    .forEach(rank -> CACHE.add(new Card(shape, rank)));
        }
    }

    private final Shape shape;
    private final Rank rank;

    public Card(final Shape shape, final Rank rank) {
        this.shape = shape;
        this.rank = rank;
    }

    public static List<Card> getCache() {
        return CACHE;
    }

    public boolean isAce() {
        return rank.isAce();
    }

    public int getScore() {
        return rank.getScore();
    }

    public Shape getShape() {
        return shape;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return shape == card.shape && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, rank);
    }
}
