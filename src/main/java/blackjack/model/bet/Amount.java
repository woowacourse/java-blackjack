package blackjack.model.bet;

public final class Amount {
    private final int value;

    public Amount(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public Amount multipliedBy(double rate) {
        int multipliedAmount = (int) Math.round(this.value * rate);
        return new Amount(multipliedAmount);
    }

    public Amount add(Amount amount) {
        if (amount == null) {
            return this;
        }
        return new Amount(this.value + amount.value);
    }

    public Amount reverse() {
        return new Amount(-this.value);
    }
}
