package blackjack.domain.participants;

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
            throw new IllegalArgumentException("베팅 금액은 " + MIN + "원 이상이어야 합니다.");
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
