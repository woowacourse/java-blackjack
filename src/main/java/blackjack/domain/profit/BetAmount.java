package blackjack.domain.profit;

public record BetAmount(double value) {

    private static final int UNIT = 10;

    public BetAmount {
        validate(value);
    }

    private void validate(double value) {
        validateNotNegative(value);
        validateNotDecimal(value);
        validateUnit(value);
    }

    private void validateNotNegative(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 0 또는 음수일 수 없습니다.");
        }
    }

    private void validateNotDecimal(double value) {
        if (value % 1 != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 소수일 수 없습니다.");
        }
    }

    private void validateUnit(double value) {
        if (value % UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 10 단위여야 합니다.");
        }
    }
}
