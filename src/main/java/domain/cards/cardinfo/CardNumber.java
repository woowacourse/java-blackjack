package domain.cards.cardinfo;

public enum CardNumber {

    A(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    J(10),
    Q(10),
    K(10);

    private static final int CONDITION_DECIDING_A_SCORE = 10;
    private static final int A_SCORE_GAP = 10;
    private final int score;

    CardNumber(int score) {
        this.score = score;
    }

    private boolean isNotAce() {
        return !this.equals(A);
    }

    public int getScore() {
        return score;
    }

    public int decideAceScore(int totalScore) {
        if (isNotAce()) {
            return 0;
        }
        if (totalScore <= CONDITION_DECIDING_A_SCORE) {
            return A.score + A_SCORE_GAP;
        }
        return A.score;
    } // TODO: getScore 와 합치기?
}
