package domain;

public class Card {
    private final Suit suit;
    private final Rank rank;

    private Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public static Card of (Suit suit, Rank rank){
        return new Card(suit, rank);
    }

    public String getName(){
        return rank.getName() + suit.getName();
    }

}
