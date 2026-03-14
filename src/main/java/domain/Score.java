package domain;

public record Score(
        int value
) {
    public boolean isBlackjack() {
        return isEqualTo(Rank.BLACKJACK_MAX_SCORE);
    }

    public boolean isBust() {
        return isGreaterThan(Rank.BLACKJACK_MAX_SCORE);
    }

    public Score add(Score target) {
        return new Score(target.value + this.value);
    }

    public Score sub(Score target) {
        return new Score(value - target.value);
    }

    public boolean isGreaterThan(Score target) {
        return value > target.value;
    }

    public boolean isGreaterThanOrEqualTo(int target) {
        return value >= target;
    }

    public boolean isLessThanOrEqualTo(Score target) {
        return value <= target.value;
    }

    public boolean isEqualTo(Score target) {
        return value == target.value;
    }
}
