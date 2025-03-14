package model.card;

public abstract class Card {

    protected final Suit suit;
    protected final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public abstract int findAdjustOrDefaultScore();

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getScore() {
        return rank.getDefaultScore();
    }
}
