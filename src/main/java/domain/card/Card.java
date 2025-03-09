package domain.card;

import static domain.card.Denomination.ACE;

public class Card {
    private final Denomination denomination;
    private final Suit suit;
    protected int value;

    public Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
        value = Denomination.parseInt(denomination);
    }

    public boolean isAce() {
        return denomination.equals(ACE);
    }

    public void setValueToZero() {
        value = 0;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

}
