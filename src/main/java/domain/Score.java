package domain;

public record Score(int value) {
    private static final int HIT_LIMIT = 21;

    public boolean isBust() {
        return value > HIT_LIMIT;
    }

    public boolean isHit() {
        return value == HIT_LIMIT;
    }
}
