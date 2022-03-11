package blackjack.domain;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public boolean isAce() {
        return denomination == Denomination.ACE;
    }

    public int getPoint() {
        return denomination.getPoint();
    }

    public String getSuitName() {
        return suit.getName();
    }

    public String getDenominationName() {
        return denomination.getName();
    }
}
