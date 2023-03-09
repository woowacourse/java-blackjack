package domain.card;

public final class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public int getScore() {
        return this.rank.getScore();
    }

    public String getText() {
        return this.rank.getNumberToPrint() + this.suit.getName();
    }

    public boolean isAce() {
        return this.rank.isAce();
    }
}
