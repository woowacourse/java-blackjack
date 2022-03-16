package blackjack.model;

public class Money {
    private static final String ERROR_NEGATIVE = "[ERROR] 금액은 음수일 수 없습니다.";
    private static final String ERROR_UNIT = "[ERROR] 금액은 1000원 단위여야 합니다.";
    private static final int UNIT = 1000;

    private final int amount;

    public Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        checkNegative(amount);
        checkUnit(amount);
    }

    private void checkNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(ERROR_NEGATIVE);
        }
    }

    private void checkUnit(int amount) {
        if ((amount % UNIT) != 0) {
            throw new IllegalArgumentException(ERROR_UNIT);
        }
    }
}
