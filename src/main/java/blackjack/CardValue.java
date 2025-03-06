package blackjack;

public enum CardValue {

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
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    ;

    private final int defaultValue;
    private final String label;

    CardValue(int defaultValue, String label) {
        this.defaultValue = defaultValue;
        this.label = label;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public String getLabel() {
        return label;
    }
}
