package domain.card;

public enum Value {

    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    ACE(11, "A");

    private final int score;
    private final String name;

    Value(final int score, final String name) {
        this.score = score;
        this.name = name;
    }

    public boolean isAce() {
        return this.equals(ACE);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
