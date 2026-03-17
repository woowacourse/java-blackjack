package vo;

import java.math.BigDecimal;

public class Bet {
    private final BigDecimal amount;

    public Bet(String betAmount) {
        validate(betAmount);
        this.amount = new BigDecimal(betAmount);
    }

    private void validate(String betAmount) {
        validateEmpty(betAmount);
        validateNumber(betAmount);
        validatePositive(betAmount);
    }

    private void validateEmpty(String betAmount) {
        if (betAmount.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 배팅액은 비어있을 수 없습니다.");
        }
    }

    private void validateNumber(String betAmount) {
        if (!betAmount.matches("\\d+")) {
            throw new IllegalArgumentException("[ERROR] 배팅액은 숫자만 입력해야 합니다.");
        }
    }

    private void validatePositive(String betAmount) {
        if (Long.parseLong(betAmount) <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅액은 1 이상의 양수여야 합니다.");
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
