package blackjack.domain.betting;

import blackjack.utils.IntegerUtils;

public class Money {

    private static final int UNIT = 1000;

    private final int amount;

    public Money(String amount) {
        this(IntegerUtils.parse(amount));
    }

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount % UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 1000원 단위여야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }

    public int multiple(double rate) {
        return (int) (amount * rate);
    }
}
