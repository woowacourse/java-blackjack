package domain.betting;

public class BettingAmount {
    private final int value;

    private BettingAmount(int value) {
        validateValue(value);
        this.value = value;
    }

    public static BettingAmount of(int value) {
        return new BettingAmount(value);
    }

    private static void validateValue(int value) {
        validatePositive(value);
        validateEven(value);
    }

    private static void validateEven(int value) {
        if (!isEven(value)) {
            throw new IllegalArgumentException("배팅 금액은 짝수만 가능합니다.");
        }
    }

    private static boolean isEven(int value) {
        return value % 2 == 0;
    }

    private static void validatePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양의 정수만 가능합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
