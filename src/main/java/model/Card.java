package model;

import static model.Denomination.ACE;
import static model.Denomination.ORIGINAL_ACE_VALUE;

public class Card {
    private final Denomination denomination;
    private final Suit suit;
    protected int value;

    public Card(Card card) {
        this.denomination = card.denomination;
        this.suit = card.suit;
        this.value = card.value;
    }

    public Card(Denomination denomination, Suit suit) {
        this.denomination = denomination;
        this.suit = suit;
        value = Denomination.parseDenominationValue(denomination.getValue());
    }

    public boolean isAce() {
        return denomination == ACE;
    }

    public boolean isOriginalAce() {
        return isAce() && value == ORIGINAL_ACE_VALUE;
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
