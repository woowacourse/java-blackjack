package blackjackgame.domain.card;

import blackjackgame.domain.Denomination;
import blackjackgame.domain.Suit;

public enum HeartCard implements Card {
    HEART_ACE(Suit.HEART, Denomination.ACE),
    HEART_TWO(Suit.HEART, Denomination.TWO),
    HEART_THREE(Suit.HEART, Denomination.THREE),
    HEART_FOUR(Suit.HEART, Denomination.FOUR),
    HEART_FIVE(Suit.HEART, Denomination.FIVE),
    HEART_SIX(Suit.HEART, Denomination.SIX),
    HEART_SEVEN(Suit.HEART, Denomination.SEVEN),
    HEART_EIGHT(Suit.HEART, Denomination.EIGHT),
    HEART_NINE(Suit.HEART, Denomination.NINE),
    HEART_TEN(Suit.HEART, Denomination.TEN),
    HEART_JACK(Suit.HEART, Denomination.JACK),
    HEART_QUEEN(Suit.HEART, Denomination.QUEEN),
    HEART_KING(Suit.HEART, Denomination.KING);

    private final Suit suit;
    private final Denomination denomination;

    HeartCard(Suit suit, Denomination denomination) {
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
