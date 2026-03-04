package domain;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public int toScore() {
        return denomination.toScore();
    }

    public boolean isAce() {
        return denomination.isTypeOf(Denomination.ACE);
    }
}
