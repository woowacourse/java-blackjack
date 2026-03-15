package domain.member;

import domain.vo.RoundResult;

public class BettingAmount {

    private static final double BONUS_RATE = 1.5;

    private final int amount;

    public BettingAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public BettingAmount applyBonus() {
        return new BettingAmount((int) (this.amount * BONUS_RATE));
    }

    private void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0이거나 음수일 수 없습니다.");
        }
    }

    public int calculateProfit(RoundResult result) {
        return result.calculateProfit(amount);
    }
}
