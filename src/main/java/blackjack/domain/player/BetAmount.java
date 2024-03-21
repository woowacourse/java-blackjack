package blackjack.domain.player;

import java.math.BigDecimal;

public record BetAmount(BigDecimal value) {

    private static final BigDecimal UNIT = new BigDecimal(10);

    public BetAmount(String value) {
        this(new BigDecimal(value));
    }

    public BetAmount {
        validateNotNegative(value);
        validateNotDecimal(value);
        validateUnit(value);
    }

    private void validateNotNegative(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 0 또는 음수일 수 없습니다.");
        }
    }

    private void validateNotDecimal(BigDecimal value) {
        if (value.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 소수일 수 없습니다.");
        }
    }

    private void validateUnit(BigDecimal value) {
        if (value.remainder(UNIT).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 10 단위여야 합니다.");
        }
    }
}
