package domain;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public int getScore() {
        return denomination.getScore();
    }

    public String getName() {
        return denomination.getName();
    }

    public String getSuit() {
        return suit.getName();
    }
}
