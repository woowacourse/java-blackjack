package domain;

import static domain.BlackJackGame.BLACKJACK_CONDITION;

public enum PlayingCardValue {
    ACE(1), TWO(2), THREE(3),
    FOUR(4), FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9),
    TEN(10), JACK(10), QUEEN(10), KING(10);

    private static final int ACE_GAP = 10;

    private final int value;

    PlayingCardValue(final int value) {
        this.value = value;
    }

    public int getValue(final int primitiveSum) {
        if (isAddableAce(primitiveSum)) {
            return this.value + ACE_GAP;
        }
        return value;
    }

    private boolean isAddableAce(int primitiveSum) {
        return this == ACE && primitiveSum + ACE_GAP <= BLACKJACK_CONDITION;
    }
}
