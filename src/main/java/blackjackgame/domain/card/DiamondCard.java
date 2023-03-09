package blackjackgame.domain.card;

import blackjackgame.domain.Suit;
import blackjackgame.domain.Denomination;

public enum DiamondCard implements Card {
    DIAMOND_ACE(Denomination.ACE),
    DIAMOND_TWO(Denomination.TWO),
    DIAMOND_THREE(Denomination.THREE),
    DIAMOND_FOUR(Denomination.FOUR),
    DIAMOND_FIVE(Denomination.FIVE),
    DIAMOND_SIX(Denomination.SIX),
    DIAMOND_SEVEN(Denomination.SEVEN),
    DIAMOND_EIGHT(Denomination.EIGHT),
    DIAMOND_NINE(Denomination.NINE),
    DIAMOND_TEN(Denomination.TEN),
    DIAMOND_JACK(Denomination.JACK),
    DIAMOND_QUEEN(Denomination.QUEEN),
    DIAMOND_KING(Denomination.KING);

    private final Suit suit = Suit.DIAMOND;
    private final Denomination denomination;

    DiamondCard(Denomination denomination) {
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
