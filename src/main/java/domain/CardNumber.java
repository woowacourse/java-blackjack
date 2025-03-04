package domain;

public enum CardNumber {
    ACE(1, 1),
    TWO(2, 2),
    THREE(3, 3),
    FOUR(4, 4),
    FIVE(5, 5),
    SIX(6, 6),
    SEVEN(7, 7),
    EIGHT(8, 8),
    NINE(9, 9),
    TEN(10, 10),
    J(11, 10),
    Q(12, 10),
    K(13, 10);

    private final int index;
    private final int value;

    CardNumber(int index, int value) {
        this.index = index;
        this.value = value;
    }


    public static CardNumber pick(int index) {
        for (CardNumber value : values()) {
            if (value.index == index) {
                return value;
            }
        }
        throw new IllegalArgumentException();
    }
}
