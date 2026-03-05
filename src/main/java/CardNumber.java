import java.util.Arrays;

public enum CardNumber {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    J(10),
    Q(10),
    K(10),
    ACE(11);

    private int value;

    CardNumber(int value) {
        this.value = value;
    }

    public static CardNumber findByValue(int targetValue) {
        return Arrays.stream(CardNumber.values())
                .filter(cardNumber -> cardNumber.value == targetValue)
                .findFirst()
                .orElseThrow();
    }

    public int toValue() {
        return value;
    }
}
