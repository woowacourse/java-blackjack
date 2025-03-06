package domain;

public enum CardNumber {
    ACE(1, 1, "A"),
    TWO(2, 2, "2"),
    THREE(3, 3, "3"),
    FOUR(4, 4, "4"),
    FIVE(5, 5, "5"),
    SIX(6, 6, "6"),
    SEVEN(7, 7, "7"),
    EIGHT(8, 8, "8"),
    NINE(9, 9, "9"),
    TEN(10, 10, "10"),
    J(11, 10, "J"),
    Q(12, 10, "Q"),
    K(13, 10, "K");

    private final int index;
    private final int value;
    private final String displayName;

    CardNumber(int index, int value, String displayName) {
        this.index = index;
        this.value = value;
        this.displayName = displayName;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
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
