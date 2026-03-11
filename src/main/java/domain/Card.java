package domain;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getScore() {
        return rank.getScore();
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    public String getRankDisplayName() {
        return rank.getDisplayName();
    }

    public String getSuitShape() {
        return suit.getShape();
    }

//    @Override
//    public String toString() {
//        return rank.getDisplayName() + suit.getShape();
//    }
}
