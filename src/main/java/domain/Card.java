package domain;

import java.util.Objects;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(final Suit suit, final Rank rank) {
        this.suit = Objects.requireNonNull(suit, "suit must not be null");
        this.rank = Objects.requireNonNull(rank, "rank must not be null");
    }

    public int getScore() {
        return rank.value();
    }

    public boolean isAce() {
        return rank.isAce();
    }

    public String displayName() {
        return rank.symbol() + suit.suit();
    }
}
