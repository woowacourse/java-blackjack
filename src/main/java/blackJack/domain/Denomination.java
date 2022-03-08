package blackJack.domain;

public enum Denomination {
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    J(10, "J"),
    Q(10, "Q"),
    K(10, "K"),
    A(11, "A");

    private final int score;
    private final String denomination;

    Denomination(int score, String denomination) {
        this.score = score;
        this.denomination = denomination;
    }

    public int getScore() {
        return score;
    }
}
