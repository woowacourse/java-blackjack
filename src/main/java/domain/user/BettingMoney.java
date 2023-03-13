package domain.user;

public class BettingMoney {
    private static final int LOWER_LIMIT = 0;
    private static final int UPPER_LIMIT = 100_000_000;

    private final int value;

    public BettingMoney(int bettingMoneyValue) {
        validate(bettingMoneyValue);
        this.value = bettingMoneyValue;
    }

    private void validate(int bettingMoneyValue) {
        if (bettingMoneyValue < LOWER_LIMIT || bettingMoneyValue > UPPER_LIMIT) {
            throw new IllegalArgumentException("베팅 금액은 0원 이상 1억원 이하여야 합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
