package blackjack.domain;

import java.math.BigDecimal;

public class BettingAmount {

    private final BigDecimal amount;

    public BettingAmount(BigDecimal amount) {
        validate(amount);
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private void validate(BigDecimal amount) {
        minus(amount);
        zero(amount);
    }

    private void minus(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("베팅 금액은 음수일 수 없습니다.");
        }
    }

    private void zero(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("배팅 금액은 0일 수 없습니다.");
        }
    }
}
