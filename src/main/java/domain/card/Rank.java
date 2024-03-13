package domain.card;

public enum Rank {

    KING(Score.valueOf(10), "K"),
    QUEEN(Score.valueOf(10), "Q"),
    JACK(Score.valueOf(10), "J"),
    TEN(Score.valueOf(10), "10"),
    NINE(Score.valueOf(9), "9"),
    EIGHT(Score.valueOf(8), "8"),
    SEVEN(Score.valueOf(7), "7"),
    SIX(Score.valueOf(6), "6"),
    FIVE(Score.valueOf(5), "5"),
    FOUR(Score.valueOf(4), "4"),
    THREE(Score.valueOf(3), "3"),
    TWO(Score.valueOf(2), "2"),
    ACE(Score.valueOf(1), "A");

    private final Score score;
    private final String description;

    Rank(Score score, String description) {
        this.score = score;
        this.description = description;
    }

    public Score getScore() {
        return score;
    }

    public String getDescription() {
        return description;
    }
}
