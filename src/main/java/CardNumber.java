public enum CardNumber {

    A(1),
    TWO(2),
    THREE(3),
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

    CardNumber(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
