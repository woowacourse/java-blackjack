package blackjack.model.card;

public enum Denomination {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    QUEEN(10),
    KING(10),
    JACK(10),
    ACE(11);

    private int score;

    Denomination(final int score) {
        this.score = score;
    }

    public static int getSize() {
        return values().length;
    }

    public static Denomination findByIndex(final int index) {
        return values()[index];
    }

    public int getScore() {
        return score;
    }
}
