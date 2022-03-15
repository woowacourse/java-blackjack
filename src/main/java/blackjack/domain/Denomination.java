package blackjack.domain;

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
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    ;

    public static final int BLACKJACK_SCORE = 21;

    private final int score;
    private final String name;

    Denomination(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int addScore(int score) {
        return this.score + score;
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
