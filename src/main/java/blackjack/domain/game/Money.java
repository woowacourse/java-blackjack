package blackjack.domain.game;

// TODO : BigDecimal 적용하기
public class Money {

    private final int value;

    public Money(final int value) {
        this.value = value;
    }

    public Money add(Money money) {
        return new Money(this.value + money.value);
    }

    public Money multiple(Money money) {
        return new Money(this.value * money.value);
    }

    public int getValue() {
        return value;
    }
}
