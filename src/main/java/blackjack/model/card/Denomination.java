package blackjack.model.card;

public enum Denomination {
    ACE(Score.from(1), "A"),
    TWO(Score.from(2), "2"),
    THREE(Score.from(3), "3"),
    FOUR(Score.from(4), "4"),
    FIVE(Score.from(5), "5"),
    SIX(Score.from(6), "6"),
    SEVEN(Score.from(7), "7"),
    EIGHT(Score.from(8), "8"),
    NINE(Score.from(9), "9"),
    TEN(Score.from(10), "10"),
    QUEEN(Score.from(10), "Q"),
    KING(Score.from(10), "K"),
    JACK(Score.from(10), "J");

    private final Score score;
    private final String name;

    Denomination(final Score score, final String name) {
        this.score = score;
        this.name = name;
    }

    public static int getSize() {
        return values().length;
    }

    public static Denomination findByIndex(final int index) {
        return values()[index];
    }

    public boolean isAce() {
        return this == ACE;
    }

    public Score getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
