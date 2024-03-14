package blackjack.model.result;

public final class BettingMoney {
    private final int amount;

    public BettingMoney(final int amount) {
        this.amount = amount;
    }

    public BettingMoney applyEarningRate(final double earningRate) {
        return new BettingMoney((int) (amount * earningRate));
    }

    public int getAmount() {
        return amount;
    }
}
