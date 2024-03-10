package domain.playingcard;

public enum PlayingCardValue {
    SMALL_ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
    EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), DEFAULT_ACE(11);

    private final int value;

    PlayingCardValue(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isAce() {
        return this == DEFAULT_ACE;
    }

    public boolean isTenValueCard() {
        return this == TEN || this == JACK || this == QUEEN || this == KING;
    }
}
