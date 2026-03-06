package domain.card;

public class Card {
    Rank rank;
    Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isAce() {
        return rank.equals(Rank.ACE);
    }

    public String name() {
        return rank.getTitle() + suit.getSymbol();
    }

    public int score() {
        return rank.getValue();
    }
}
