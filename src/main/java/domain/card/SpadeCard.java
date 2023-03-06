package domain.card;

import domain.Denomination;
import domain.Suit;

public enum SpadeCard implements Card {
    SPADE_ACE(Suit.SPADE, Denomination.ACE),
    SPADE_TWO(Suit.SPADE, Denomination.TWO),
    SPADE_THREE(Suit.SPADE, Denomination.THREE),
    SPADE_FOUR(Suit.SPADE, Denomination.FOUR),
    SPADE_FIVE(Suit.SPADE, Denomination.FIVE),
    SPADE_SIX(Suit.SPADE, Denomination.SIX),
    SPADE_SEVEN(Suit.SPADE, Denomination.SEVEN),
    SPADE_EIGHT(Suit.SPADE, Denomination.EIGHT),
    SPADE_NINE(Suit.SPADE, Denomination.NINE),
    SPADE_TEN(Suit.SPADE, Denomination.TEN),
    SPADE_JACK(Suit.SPADE, Denomination.JACK),
    SPADE_QUEEN(Suit.SPADE, Denomination.QUEEN),
    SPADE_KING(Suit.SPADE, Denomination.KING);

    private final Suit suit;
    private final Denomination denomination;

    SpadeCard(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }

    @Override
    public String getSymbol() {
        return denomination.getNumber() + suit.getPattern();
    }

    @Override
    public int getScore() {
        return denomination.getScore();
    }
}
