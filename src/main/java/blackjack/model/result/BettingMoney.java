package blackjack.model.result;

public final class BettingMoney {
    private final int amount;

    public BettingMoney(final int amount) {
        this.amount = amount;
    }

    public int applyEarningRate(final double earningRate) {
        return (int) (amount * earningRate);
    }

    public boolean isMultipleOfTen() {
        return amount % 10 == 0;
    }

    public int getAmount() {
        return amount;
    }
}
