package domain.card;

public enum Rank {

    KING(new Score(10), "K"),
    QUEEN(new Score(10), "Q"),
    JACK(new Score(10), "J"),
    TEN(new Score(10), "10"),
    NINE(new Score(9), "9"),
    EIGHT(new Score(8), "8"),
    SEVEN(new Score(7), "7"),
    SIX(new Score(6), "6"),
    FIVE(new Score(5), "5"),
    FOUR(new Score(4), "4"),
    THREE(new Score(3), "3"),
    TWO(new Score(2), "2"),
    ACE(new Score(1), "A");

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
