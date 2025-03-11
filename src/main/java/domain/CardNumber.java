package domain;

public enum CardNumber {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    J(10, "J"),
    Q(10, "Q"),
    K(10, "K"),
    ;

    private final int worth;
    private final String displayName;

    CardNumber(int value, String displayName) {
        this.worth = value;
        this.displayName = displayName;
    }

    public int getWorth() {
        return worth;
    }

    public String getDisplayName() {
        return displayName;
    }
}
