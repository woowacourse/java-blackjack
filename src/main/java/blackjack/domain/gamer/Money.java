package blackjack.domain.gamer;

public record Money(long amount) {

    private static final Money ZERO_AMOUNT = new Money(0L);

    public static Money getZeroAmountMoney() {
        return ZERO_AMOUNT;
    }

    public Money add(Money money) {
        return new Money(amount + money.amount());
    }

    public Money multiplyByRatio(double ratio) {
        return new Money((long) (amount * ratio));
    }
}
