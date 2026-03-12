package domain;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
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
