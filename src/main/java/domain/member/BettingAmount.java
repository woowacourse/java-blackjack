package domain.member;

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

    public int getAmount() {
        return amount;
    }

    private void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("배팅 금액은 음수일 수 없습니다.");
        }
    }
}
