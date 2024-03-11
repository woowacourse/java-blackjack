package blackjack.model.card;

public enum Denomination {
    ACE(new Score(1), "A"),
    TWO(new Score(2), "2"),
    THREE(new Score(3), "3"),
    FOUR(new Score(4), "4"),
    FIVE(new Score(5), "5"),
    SIX(new Score(6), "6"),
    SEVEN(new Score(7), "7"),
    EIGHT(new Score(8), "8"),
    NINE(new Score(9), "9"),
    TEN(new Score(10), "10"),
    QUEEN(new Score(10), "Q"),
    KING(new Score(10), "K"),
    JACK(new Score(10), "J");

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
