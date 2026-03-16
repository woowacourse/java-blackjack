package domain;

public class BettingAmount {

    private final int amount;

    private BettingAmount(int amount) {
        this.amount = amount;
    }

    public static BettingAmount of(int amount) {
        return new BettingAmount(amount);
    }

    public int value() {
        return amount;
    }
}
