package vo;

public record BettingMoney(int value) {
    public BettingMoney {
        validateNotPositive(value);
    }

    private void validateNotPositive(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수로 구성되어야 합니다.");
        }
    }
}
