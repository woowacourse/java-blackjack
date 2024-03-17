package model.betting;

import java.math.BigDecimal;

public class Bet {

    private final BigDecimal amount;

    public Bet(String amountInput) {
        BigDecimal amount = convert(amountInput);
        validate(amount);
        this.amount = amount;
    }

    private BigDecimal convert(String amount) {
        try {
            return new BigDecimal(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("금은 숫자만 입력 가능합니다.");
        }
    }

    private void validate(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("배팅금은 0보다 큰 정수만 가능합니다.");
        }
        if (amount.remainder(BigDecimal.TEN).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("배팅금은 10원 단위만 가능합니다.");
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
