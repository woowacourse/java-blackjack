package blackjack.model.result;

public final class BettingMoney {
    private static final String INVALID_BETTING_MONEY = "배팅 금액은 10의 배수입니다";
    private static final int BETTING_MONEY_UNIT = 10;

    private final int amount;

    public BettingMoney(final int amount) {
        validate(amount);
        this.amount = amount;
    }

    public void validate(final int amount) {
        if (amount % BETTING_MONEY_UNIT != 0) {
            throw new IllegalArgumentException(INVALID_BETTING_MONEY);
        }
    }

    public int applyEarningRate(final double earningRate) {
        return (int) (amount * earningRate);
    }
}
