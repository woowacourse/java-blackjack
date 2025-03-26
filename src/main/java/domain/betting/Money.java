package domain.betting;

public record Money(int value) {
    public Money {
        validateMoneyIsPositive(value);
    }

    private void validateMoneyIsPositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수여야 합니다.");
        }
    }
}
