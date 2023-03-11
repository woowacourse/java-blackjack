package blackjack.domain.bet;

public class Money {

    public static final Money NO_BENEFIT = new Money(0);
    private static final double BLACK_JACK_ADDITIONAL_BENEFIT = 1.5;

    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public Money getNegativeMoney() {
        return new Money(-value);
    }

    public Money getBlackJackMoney() {
        return new Money((int) (BLACK_JACK_ADDITIONAL_BENEFIT * value));
    }

    public int getValue() {
        return value;
    }
}
