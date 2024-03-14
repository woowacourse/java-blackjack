package blackjack.model.result;

public final class BettingMoney {
    private static final BettingMoney ZERO_MONEY = new BettingMoney(0);

    private final double amount;

    private BettingMoney(final double amount) {
        this.amount = amount;
    }

    public static BettingMoney from(final double amount) {
        if (amount == 0) {
            return ZERO_MONEY;
        }
        return new BettingMoney(amount);
    }

    public BettingMoney applyEarningRate(final double earningRate) {
        return BettingMoney.from(amount + amount * earningRate);
    }

    public BettingMoney minus(final BettingMoney otherMoney) {
        return BettingMoney.from(amount - otherMoney.amount);
    }

    public double getAmount() {
        return amount;
    }
}
