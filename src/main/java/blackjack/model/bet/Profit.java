package blackjack.model.bet;

public final class Profit {
    private static final int RATE_TO_NEGATIVE = -1;

    private final Amount amount;

    Profit(Amount amount) {
        this.amount = amount;
    }

    Profit add(Profit profit) {
        if (profit == null) {
            return this;
        }
        return new Profit(this.amount.add(profit.amount));
    }

    Profit reverse() {
        return new Profit(this.amount.multipliedBy(RATE_TO_NEGATIVE));
    }

    public int getValue() {
        return this.amount.getValue();
    }
}
