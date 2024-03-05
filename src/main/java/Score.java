public class Score {

    private static final int BUST_THRESHOLD = 21;
    private static final int DEALER_THRESHOLD = 16;
    private static final int A_SCORE_CONDITION = 10;
    private static final int A_SCORE_GAP = 10;

    private int value;

    public Score(int value) {
        this.value = value;
    }

    public int addScore(Card card) {
        if (card.isAce() && value > A_SCORE_CONDITION) {
            value += card.score() - A_SCORE_GAP;
            return value;
        }
        value += card.score();
        return value;
    }

    public boolean isLowerThanBustThreshold() {
        return value <= BUST_THRESHOLD;
    }

    public boolean isLowerThanDealerThreshold() {
        return value <= DEALER_THRESHOLD;
    }
}
