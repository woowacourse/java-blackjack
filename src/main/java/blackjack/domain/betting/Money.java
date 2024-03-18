package blackjack.domain.betting;

public record Money(int value) {

    public Money {
        validateMinimum(value);
    }

    private void validateMinimum(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("돈의 액수는 0 이상이어야 합니다.");
        }
    }
}
