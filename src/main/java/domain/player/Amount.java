package domain.player;

public class Amount {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100_000_000;
    private final int amount;

    public Amount(final int amount) {
        validateRange(amount);
        this.amount = amount;
    }

    private static void validateRange(final int amount) {
        if (amount < MIN_RANGE || amount > MAX_RANGE) {
            throw new IllegalArgumentException();
        }
    }

    public int getAmount() {
        return amount;
    }
}
