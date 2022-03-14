package domain.card;

public class Card {

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getPoint() {
        return rank.getPoint();
    }

    public boolean isAce() {
        return rank.isAce();
    }

    public String combineRankAndSuit() {
        return rank.getRank() + suit.getSuit();
    }
}
