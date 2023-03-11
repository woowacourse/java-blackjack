package blackjack.domain;

public class Money {

    private static final double BLACK_JACK_ADDITIONAL_BENEFIT = 1.5;
    public static final Money NO_BENEFIT = new Money(0);

    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public Money getNegative() {
        return new Money(-value);
    }

    public Money getBlackJackMoney() {
        return new Money((int) (BLACK_JACK_ADDITIONAL_BENEFIT * value));
    }

    public int getValue() {
        return value;
    }
}
