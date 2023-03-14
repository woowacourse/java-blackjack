package blackjack.domain.bet;

public class Money {

    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public Money getNegativeMoney() {
        return new Money(-value);
    }

    public int getValue() {
        return value;
    }
}
