package blackjack.domain;

public enum CardNumber {
    //TODO: ACE가 11 또는 1을 판단하는 로직을 어디서 구현할지
    ACE(11),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private final int value;

    CardNumber(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
