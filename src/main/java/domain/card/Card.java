package domain.card;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isAce() {
        return rank.equals(Rank.ACE);
    }

    public Rank rank() {
        return rank;
    }

    public Suit suit() {
        return suit;
    }

    public int score() {
        return rank.value();
    }
}
