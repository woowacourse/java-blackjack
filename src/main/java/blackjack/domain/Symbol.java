package blackjack.domain;

public enum Symbol {
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
    KING(10, "K");

    private final int score;
    private final String name;

    Symbol(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public int calculateScore(int sum) {
        if (this == ACE) {
            int elevenAce = 11;
            if (sum + elevenAce <= 21) {
                return elevenAce;
            }
        }
        return score;
    }

    public String getName() {
        return name;
    }
}
