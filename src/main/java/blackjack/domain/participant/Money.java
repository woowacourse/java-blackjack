package blackjack.domain.participant;

import blackjack.utils.IntegerUtils;

public class Money {

    private final int amount;

    public Money(String amount) {
        this(IntegerUtils.parse(amount));
    }

    public Money(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
