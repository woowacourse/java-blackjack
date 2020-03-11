package blackjack.card;

public enum Symbol {
    ACE(1, "A"),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private int score;
    private String name;

    Symbol(int score) {
        this.score = score;
        this.name = String.valueOf(score);
    }

    Symbol(int score, String name) {
        this.score = score;
        this.name = name;
    }
}
