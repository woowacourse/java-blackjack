package blackjack.domain;

public record Score(int value) {
    private static final int MINIMUM_VALUE = 0;

    public Score {
        validateRange(value);
    }

    private void validateRange(int value) {
        if (value < MINIMUM_VALUE) {
            throw new IllegalArgumentException("음수는 점수로 사용될 수 없습니다.");
        }
    }
}
