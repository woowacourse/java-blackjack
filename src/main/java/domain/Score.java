package domain;

public record Score(
        int value
) {
    public static final Score ZERO = new Score(0);
    public static final Score ONE = new Score(1);
    public static final Score TWO = new Score(2);
    public static final Score THREE = new Score(3);
    public static final Score FOUR = new Score(4);
    public static final Score FIVE = new Score(5);
    public static final Score SIX = new Score(6);
    public static final Score SEVEN = new Score(7);
    public static final Score EIGHT = new Score(8);
    public static final Score NINE = new Score(9);
    public static final Score TEN = new Score(10);
    public static final Score BLACKJACK = new Score(21);
    public static final Score ACE_MAX = new Score(11);
    public static final Score ACE_MIN = new Score(1);

    public boolean isBlackjack() {
        return isEqualTo(BLACKJACK);
    }

    public boolean isBust() {
        return isGreaterThan(BLACKJACK);
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
