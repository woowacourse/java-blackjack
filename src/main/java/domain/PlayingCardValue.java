package domain;

import static domain.constant.GameOption.BLACKJACK_CONDITION;

public enum PlayingCardValue {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);

    private final int value;

    PlayingCardValue(final int value) {
        this.value = value;
    }

    public int getValue(final int inputValue) {
        if (this == ACE && inputValue + this.value > BLACKJACK_CONDITION) {
            return ACE.value - 10;
        }
        return value;
    }
}
