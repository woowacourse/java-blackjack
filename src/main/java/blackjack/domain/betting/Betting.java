package blackjack.domain.betting;

public class Betting {

    private static final int MAXIMUM_AMOUNT = 100_000_000;
    private static final int MINIMUM_AMOUNT = 1_000;

    private final int amount;

    public Betting(final int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(final int amount) {
        if (amount > MAXIMUM_AMOUNT) {
            throw new IllegalArgumentException("최대 배팅 금액은 1억원입니다.");
        }
        if (amount < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("최소 배팅 금액은 1000원입니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}
