package blackjack.domain.bet;

public class Money {

    private static final int MAX = 1_000_000_000;
    private static final int MIN = 0;

    private final int amount;

    public Money(int amount) {
        validateRange(amount);
        this.amount = amount;
    }

    public Profit calculateProfit(double leverage) {
        return new Profit((int) (amount * leverage));
    }

    private void validateRange(int amount) {
        if (amount < MIN || MAX < amount) {
            throw new IllegalArgumentException("[ERROR] 금액은 " + MIN + "부터 " + MAX + "이하까지 가능합니다.");
        }
    }
}
