package participant;

public record Money(int price) {
    public static Money bet(int price) {
        return new Money(price);
    }

    public static Money multiply(Money bettingPrice, double payoutRate) {
        return new Money((int) (bettingPrice.price() * payoutRate));
    }

    public static Money createZero() {
        return new Money(0);
    }
}
