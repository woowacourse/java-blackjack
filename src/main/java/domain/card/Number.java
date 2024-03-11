package domain.card;

public enum Number {
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
    JACK(10),
    QUEEN(10),
    KING(10);

    private static final int MAX_SOFT_ACE_CONVERTABLE_SUM = 11;
    private static final int SOFT_ACE_CONVERT_VALUE = 10;
    private final int number;

    Number(int number) {
        this.number = number;
    }

    public int getValue() {
        return number;
    }

    public static int sumContainingSoftAce(int sum) {
        if (sum <= MAX_SOFT_ACE_CONVERTABLE_SUM) {
            return sum + SOFT_ACE_CONVERT_VALUE;
        }
        return sum;
    }
}
