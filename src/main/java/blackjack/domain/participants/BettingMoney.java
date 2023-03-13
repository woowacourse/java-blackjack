package blackjack.domain.participants;

import static blackjack.domain.ExceptionMessage.INVALID_BETTING_AMOUNT_FORMAT;

import java.math.BigDecimal;

public class BettingMoney {

    public static final BigDecimal MIN = new BigDecimal("1000");

    private final BigDecimal amount;

    public BettingMoney(final BigDecimal amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(final BigDecimal amount) {
        if (amount.compareTo(MIN) < 0) {
            throw new IllegalArgumentException(String.format(INVALID_BETTING_AMOUNT_FORMAT, MIN.intValue()));
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
