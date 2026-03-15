package domain;

import java.math.BigDecimal;

public record Profit(BigDecimal value) {
    private static final BigDecimal MAX_PROFIT_LIMIT = new BigDecimal("100000000000000");

    public Profit {
        validateNull(value);
        validateLimit(value);
    }

    private void validateNull(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("수익 금액은 필수입니다.");
        }
    }

    private void validateLimit(BigDecimal value) {
        if (value.abs().compareTo(MAX_PROFIT_LIMIT) > 0) {
            throw new IllegalArgumentException("수익 금액이 시스템 허용 범위를 초과했습니다.");
        }
    }

    public Profit negate() {
        return new Profit(value.negate());
    }
}
