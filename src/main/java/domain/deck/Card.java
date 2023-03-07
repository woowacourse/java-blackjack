package domain.deck;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit.getSuit();
    }

    public String getRank() {
        return rank.getRank();
    }

    public int getScore() {
        return rank.getScore();
    }
}
