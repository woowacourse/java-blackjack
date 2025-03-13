package blackjack.model.player.money;

public class Money {

    protected int value;

    protected Money(final int value) {
        this.value = value;
    }

    public static Money from(final int value) {
        return new Money(value);
    }

    public static Money zero() {
        return new Money(0);
    }

    public final void add(final int money) {
        value += money;
    }

    public final void subtract(final int money) {
        value -= money;
    }

    public int getValue() {
        return value;
    }
}
