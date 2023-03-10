package blackjack.domain.player;

public final class Money {

    private static final Money zero = new Money(0);

    private final int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    public static Money zero() {
        return zero;
    }

    public int getAmount() {
        return amount;
    }
}
