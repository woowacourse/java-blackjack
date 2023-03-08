package blackjackgame.domain.card;

import blackjackgame.domain.Suit;
import blackjackgame.domain.Denomination;

public enum CloverCard implements Card {
    CLOVER_ACE(Denomination.ACE),
    CLOVER_TWO(Denomination.TWO),
    CLOVER_THREE(Denomination.THREE),
    CLOVER_FOUR(Denomination.FOUR),
    CLOVER_FIVE(Denomination.FIVE),
    CLOVER_SIX(Denomination.SIX),
    CLOVER_SEVEN(Denomination.SEVEN),
    CLOVER_EIGHT(Denomination.EIGHT),
    CLOVER_NINE(Denomination.NINE),
    CLOVER_TEN(Denomination.TEN),
    CLOVER_JACK(Denomination.JACK),
    CLOVER_QUEEN(Denomination.QUEEN),
    CLOVER_KING(Denomination.KING);

    private final Suit suit = Suit.CLOVER;
    private final Denomination denomination;

    CloverCard(Denomination denomination) {
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
