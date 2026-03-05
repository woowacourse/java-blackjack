package domain;

public class Card {
    private final Rank rank;
    private final Suit suit;

    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card of(Rank rank, Suit suit) {
        return new Card(rank, suit);
    }

    public String getName() {
        return rank.getName() + suit.getName();
    }

    public int getScore() {
        return rank.getValue();
    }

}
