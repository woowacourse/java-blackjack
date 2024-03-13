package domain;

public class BetAmount {

    private final int amount;

    public BetAmount(int amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    private void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 배팅 금액입니다.");
        }
    }
}
