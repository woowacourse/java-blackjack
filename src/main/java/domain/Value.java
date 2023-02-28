package domain;

public enum Value {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    J(10),
    Q(10),
    K(10),
    A(11);

    private final int score;

    Value(final int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public boolean isAce() {
        return this.equals(A);
    }
}
