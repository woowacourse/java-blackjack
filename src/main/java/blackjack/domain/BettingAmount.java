package blackjack.domain;

public class BettingAmount {

    private final int amount;

    public BettingAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    private void validate(int amount) {
        minus(amount);
        zero(amount);
    }

    private void minus(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("베팅 금액은 음수일 수 없습니다.");
        }
    }

    private void zero(int amount) {
        if (amount == 0) {
            throw new IllegalArgumentException("배팅 금액은 0일 수 없습니다.");
        }
    }
}
