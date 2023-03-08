package blackjackgame.domain.card;

import blackjackgame.domain.Denomination;
import blackjackgame.domain.Suit;

public enum SpadeCard implements Card {
    SPADE_ACE(Denomination.ACE),
    SPADE_TWO(Denomination.TWO),
    SPADE_THREE(Denomination.THREE),
    SPADE_FOUR(Denomination.FOUR),
    SPADE_FIVE(Denomination.FIVE),
    SPADE_SIX(Denomination.SIX),
    SPADE_SEVEN(Denomination.SEVEN),
    SPADE_EIGHT(Denomination.EIGHT),
    SPADE_NINE(Denomination.NINE),
    SPADE_TEN(Denomination.TEN),
    SPADE_JACK(Denomination.JACK),
    SPADE_QUEEN(Denomination.QUEEN),
    SPADE_KING(Denomination.KING);

    private final Suit suit = Suit.SPADE;
    private final Denomination denomination;

    SpadeCard(Denomination denomination) {
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
