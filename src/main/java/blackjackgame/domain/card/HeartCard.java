package blackjackgame.domain.card;

import blackjackgame.domain.Denomination;
import blackjackgame.domain.Suit;

public enum HeartCard implements Card {
    HEART_ACE(Denomination.ACE),
    HEART_TWO(Denomination.TWO),
    HEART_THREE(Denomination.THREE),
    HEART_FOUR(Denomination.FOUR),
    HEART_FIVE(Denomination.FIVE),
    HEART_SIX(Denomination.SIX),
    HEART_SEVEN(Denomination.SEVEN),
    HEART_EIGHT(Denomination.EIGHT),
    HEART_NINE(Denomination.NINE),
    HEART_TEN(Denomination.TEN),
    HEART_JACK(Denomination.JACK),
    HEART_QUEEN(Denomination.QUEEN),
    HEART_KING(Denomination.KING);

    private final Suit suit = Suit.HEART;
    private final Denomination denomination;

    HeartCard(Denomination denomination) {
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
