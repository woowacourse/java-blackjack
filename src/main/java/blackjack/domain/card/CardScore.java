package blackjack.domain.card;

public enum CardScore {
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

    CardScore(int score) {
        this.score = score;
    }

    public int get() {
        return score;
    }
}
