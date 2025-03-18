package participant.value;

public record Money(int price) {
    public static final Money ZERO = new Money(0);

    public static Money bet(int price) {
        return new Money(price);
    }

    public static Money multiply(Money bettingPrice, double payoutRate) {
        return new Money((int) (bettingPrice.price() * payoutRate));
    }

    public Money add(Money other) {
        return new Money(price + other.price);
    }

    public Money negate() {
        return new Money(-price);
    }
}
