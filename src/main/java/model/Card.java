package model;

public class Card {

    private final SuitType suit;
    private final RankType rank;


    public Card(SuitType suit, RankType rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public SuitType getSuit() {
        return suit;
    }

    public RankType getRank() {
        return rank;
    }

    public String getCard() {
        return rank.getDisplayName() + suit.getDisplayName();
    }
}
