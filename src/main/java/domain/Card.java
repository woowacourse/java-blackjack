package domain;

import static domain.Denomination.ACE;

public class Card {
    private Denomination denomination;
    private Suit suit;
    protected int value;

    public Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
        value = Denomination.parseInt(denomination);
    }

    public boolean isAce() {
        return denomination.equals(ACE);
    }

    public int getValue() {
        return value;
    }

}
