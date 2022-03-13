package blackjack.domain.card;

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

    private static final int BLACKJACK_SCORE = 21;
    private static final int BONUS_ACE_SCORE = 11;

    private final int score;
    private final String name;

    Denomination(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int addScore(int score) {
        if (this == ACE) {
            return calculateAceScore(score);
        }

        return this.score + score;
    }

    private int calculateAceScore(int score) {
        if ((score + BONUS_ACE_SCORE) > BLACKJACK_SCORE) {
            return score + ACE.score;
        }

        return score + BONUS_ACE_SCORE;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
