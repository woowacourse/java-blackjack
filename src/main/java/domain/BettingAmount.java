package domain;

public class BettingAmount {
    private final int amount;

    public BettingAmount(int amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    private void validatePositive(int input) {
        if (input <= 0) {
            throw new IllegalArgumentException("베팅 금액은 양의 정수여야 합니다");
        }
    }

    public int getAmount() {
        return amount;
    }
}
