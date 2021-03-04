package blackjack.domain.card;

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

    private static final int ZERO = 0;
    private static final int ACE_BONUS_SCORE = 10;
    private static final int MAXIMUM_SCORE_FOR_ACE_BONUS_SCORE = 11;

    private final int score;
    private final String name;

    Symbol(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int calculateAceBonusScore(int score, int aceCounts) {
        int i = ZERO;
        int aceBonusScore = ZERO;
        while (i < aceCounts && score <= MAXIMUM_SCORE_FOR_ACE_BONUS_SCORE) {
            aceBonusScore += ACE_BONUS_SCORE;
            score += ACE_BONUS_SCORE;
            i++;
        }
        return aceBonusScore;
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
