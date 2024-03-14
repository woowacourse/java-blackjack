package blackjack.model.result;

public final class BettingMoney {
    private final double amount;

    public BettingMoney(final double amount) {
        this.amount = amount;
    }

    public BettingMoney applyEarningRate(final double earningRate) {
        return new BettingMoney(amount + amount * earningRate);
    }

    public BettingMoney minus(final BettingMoney otherMoney) {
        return new BettingMoney(amount - otherMoney.amount);
    }

    public double getAmount() {
        return amount;
    }
}
