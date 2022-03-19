package blackjack.model.bet;

public final class Amount {
    private final int value;

    Amount(int value) {
        this.value = value;
    }

    Amount multipliedBy(double rate) {
        int multipliedAmount = (int) Math.round(this.value * rate);
        return new Amount(multipliedAmount);
    }

    Amount add(Amount amount) {
        if (amount == null) {
            return this;
        }
        return new Amount(this.value + amount.value);
    }

    public int getValue() {
        return this.value;
    }
}
