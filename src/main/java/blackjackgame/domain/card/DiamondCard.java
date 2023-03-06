package blackjackgame.domain.card;

import blackjackgame.domain.Suit;
import blackjackgame.domain.Denomination;

public enum DiamondCard implements Card {
    DIAMOND_ACE(Suit.DIAMOND, Denomination.ACE),
    DIAMOND_TWO(Suit.DIAMOND, Denomination.TWO),
    DIAMOND_THREE(Suit.DIAMOND, Denomination.THREE),
    DIAMOND_FOUR(Suit.DIAMOND, Denomination.FOUR),
    DIAMOND_FIVE(Suit.DIAMOND, Denomination.FIVE),
    DIAMOND_SIX(Suit.DIAMOND, Denomination.SIX),
    DIAMOND_SEVEN(Suit.DIAMOND, Denomination.SEVEN),
    DIAMOND_EIGHT(Suit.DIAMOND, Denomination.EIGHT),
    DIAMOND_NINE(Suit.DIAMOND, Denomination.NINE),
    DIAMOND_TEN(Suit.DIAMOND, Denomination.TEN),
    DIAMOND_JACK(Suit.DIAMOND, Denomination.JACK),
    DIAMOND_QUEEN(Suit.DIAMOND, Denomination.QUEEN),
    DIAMOND_KING(Suit.DIAMOND, Denomination.KING);

    private final Suit suit;
    private final Denomination denomination;

    DiamondCard(Suit suit, Denomination denomination) {
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
