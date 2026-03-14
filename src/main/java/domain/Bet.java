package domain;

import java.math.BigDecimal;

public record Bet(BigDecimal amount) {
    private static final BigDecimal MINIMUM_BETTING_AMOUNT = new BigDecimal("10");
    private static final BigDecimal MAXIMUM_BETTING_AMOUNT = new BigDecimal("1000000000");
    private static final BigDecimal UNIT = new BigDecimal("10");

    public Bet {
        validateNull(amount);
        validateRange(amount);
        validateUnit(amount);
    }

    private void validateNull(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("베팅 금액은 필수입니다.");
        }
    }

    private void validateRange(BigDecimal amount) {
        if (amount.compareTo(MINIMUM_BETTING_AMOUNT) < 0) {
            throw new IllegalArgumentException("최소 베팅 금액은 10원입니다.");
        }
        if (amount.compareTo(MAXIMUM_BETTING_AMOUNT) > 0) {
            throw new IllegalArgumentException("최대 베팅 금액은 10억 원을 초과할 수 없습니다.");
        }
    }

    private void validateUnit(BigDecimal amount) {
        if (amount.remainder(UNIT).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("베팅은 10원 단위로만 가능합니다.");
        }
    }
}
