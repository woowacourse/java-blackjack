package domain.score;

public record Score(
        int value
) {
    private static final int MIN_VALUE = 0;

    public Score {
        validateValue(value);
    }


    private void validateValue(int value) {
        if (value < MIN_VALUE) {
            throw new IllegalArgumentException("Score 값의 음수일 수 없습니다.");
        }
    }

    public boolean isHigher(Score score) {
        return this.value > score.value;
    }

    public boolean isEqual(Score score) {
        return this.value == score.value;
    }

    public Score sum(Score score) {
        return new Score(score.value + this.value);
    }
}
