package domain;

public class BettingAmount {

    private long amount;

    private BettingAmount(int amount) {
        this.amount = amount;
    }

    public static BettingAmount of(int amount) {
        return new BettingAmount(amount);
    }

    public void applyBlackjackBonus() {
        amount = (int) (amount * 1.5);
    }

    public long value() {
        return amount;
    }
}
