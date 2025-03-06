package domain.card;

public enum Number {
    A(1),
    TWO(2),
    THREE(2),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    J(10),
    Q(10),
    K(10);

    private final int score;

    Number(int score) {
        this.score = score;
    }

    public int calculateScore(int sum) {
        return score;
    }

    public boolean isA() {
        return score == A.score;
    }


    public int getScore() {
        return score;
    }
}