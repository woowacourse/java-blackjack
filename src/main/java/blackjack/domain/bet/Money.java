package blackjack.domain.bet;

public class Money {

    private static final int MAX = 1_000_000_000;
    private static final int MIN = -1_000_000_000;

    private final int amount;

    public Money(int amount) {
        validateRange(amount);
        this.amount = amount;
    }

    public Money multiply(double scope) {
        return new Money((int) (amount * scope));
    }

    public Profit calculateProfit(double leverage) {
        return new Profit((int) (amount * leverage));
    }

    public Money add(Money other) {
        return new Money(amount + other.amount);
    }

    public Money inverse() {
        return new Money(-1 * amount);
    }

    public int getAmount() {
        return amount;
    }

    public void validateRange(int amount) {
        if (amount < MIN || MAX < amount) {
            throw new IllegalArgumentException("[ERROR] 금액은 " + MIN + "부터 " + MAX + "이하까지 가능합니다.");
        }
    }
}
