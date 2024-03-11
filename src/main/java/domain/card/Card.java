package domain.card;

import java.util.*;

public class Card {

    private static final List<Card> CACHE;

    static {
        List<Card> initialCards = new ArrayList<>();
        for (Shape shape : Shape.values()) {
            Arrays.stream(Rank.values())
                    .forEach(rank -> initialCards.add(new Card(shape, rank)));
        }
        CACHE = initialCards;
    }

    private final Shape shape;
    private final Rank rank;

    public Card(final Shape shape, final Rank rank) {
        this.shape = shape;
        this.rank = rank;
    }

    public static List<Card> getCache() {
        return new ArrayList<>(CACHE);
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
