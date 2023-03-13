package domain.deck;

public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String suit() {
        return suit.getSuit();
    }

    public Rank getRank() {
        return rank;
    }

    public String rank() {
        return rank.getRank();
    }

    public int getScore() {
        return rank.getScore();
    }
}
