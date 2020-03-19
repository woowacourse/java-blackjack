package second.domain.gamer;

public class Money {
    private final int value;

    public Money() {
        this.value = 0;
    }

    public Money(final int value) {
        valid(value);
        this.value = value;
    }

    private void valid(int money) {
        if (money < 0) {
            throw new InvalidMoneyException();
        }
    }
}
