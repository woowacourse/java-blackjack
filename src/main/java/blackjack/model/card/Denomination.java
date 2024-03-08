package blackjack.model.card;

public enum Denomination {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    JACK(10, "J");

    private final int score;
    private final String name;

    Denomination(final int score, final String name) {
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

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
