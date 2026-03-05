package model;

public enum CardValue {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13);

    private final int value;

    CardValue(int value) {
        this.value = value;
    }

    public static CardValue from(int value) {
        for (CardValue cardValue : values()) {
            if (cardValue.value == value) {
                return cardValue;
            }
        }
        throw new IllegalArgumentException("존재하지 않은 카드 번호입니다.");
    }

    public int getValue() {
        return this.value;
    }

    public boolean isAce() {
        return this.value == 1;
    }
}
