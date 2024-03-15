package domain;

public class Card {

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isAceCard() {
        return this.rank.isAce();
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public String getRankText() {
        return rank.getText();
    }

    public String getSuit() {
        return suit.getName();
    }
}
