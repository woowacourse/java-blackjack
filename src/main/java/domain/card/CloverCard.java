package domain.card;

import domain.Denomination;
import domain.Suit;

public enum CloverCard implements Card {
    CLOVER_ACE(Suit.CLOVER, Denomination.ACE),
    CLOVER_TWO(Suit.CLOVER, Denomination.TWO),
    CLOVER_THREE(Suit.CLOVER, Denomination.THREE),
    CLOVER_FOUR(Suit.CLOVER, Denomination.FOUR),
    CLOVER_FIVE(Suit.CLOVER, Denomination.FIVE),
    CLOVER_SIX(Suit.CLOVER, Denomination.SIX),
    CLOVER_SEVEN(Suit.CLOVER, Denomination.SEVEN),
    CLOVER_EIGHT(Suit.CLOVER, Denomination.EIGHT),
    CLOVER_NINE(Suit.CLOVER, Denomination.NINE),
    CLOVER_TEN(Suit.CLOVER, Denomination.TEN),
    CLOVER_JACK(Suit.CLOVER, Denomination.JACK),
    CLOVER_QUEEN(Suit.CLOVER, Denomination.QUEEN),
    CLOVER_KING(Suit.CLOVER, Denomination.KING);

    private final Suit suit;
    private final Denomination denomination;

    CloverCard(Suit suit, Denomination denomination) {
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
