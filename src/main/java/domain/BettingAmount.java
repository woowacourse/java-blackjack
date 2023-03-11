package domain;

public class BettingAmount {
    private final int amount;

    public BettingAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("베팅 금액은 음수가 될 수 없습니다.");
        }
        this.amount = amount;
    }
}
