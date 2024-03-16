package model.betting;

public class Bet {

    private final int amount;

    public Bet(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("배팅금은 0보다 큰 정수만 가능합니다.");
        }
        if (amount % 10 != 0) {
            throw new IllegalArgumentException("배팅금은 10원 단위만 가능합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
