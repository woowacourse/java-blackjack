package blackjack.model.result;

public final class BettingMoney {
    private final double amount;

    public BettingMoney(final double amount) {
        this.amount = amount;
    }

    public BettingMoney applyEarningRate(final double earningRate) {
        return new BettingMoney(amount * earningRate);
    }

    public double getAmount() {
        return amount;
    }
}
