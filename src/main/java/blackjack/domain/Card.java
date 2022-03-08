package blackjack.domain;

public class Card {

    private final Suit suit;
    private final Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    public boolean isAce() {
        if (denomination == Denomination.ACE) {
            return true;
        }
        return false;
    }

    public int getPoint() {
        return denomination.getPoint();
    }
}
