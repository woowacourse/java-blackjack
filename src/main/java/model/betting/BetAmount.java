package model.betting;

public class BetAmount {

    private final int amount;

    public BetAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("배팅금은 0보다 큰 정수만 가능합니다.");
        }
    }
}
