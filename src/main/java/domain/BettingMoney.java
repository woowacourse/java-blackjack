package domain;

public class BettingMoney {

    private final int amount;

    public BettingMoney(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validatePositive(amount);
    }

    private void validatePositive(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("배팅 금액은 0 이상이어야 합니다.");
        }
    }
}
