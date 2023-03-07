package blackjack.domain.game;

import blackjack.domain.participant.Score;

public enum Result {
    WIN,
    DRAW,
    LOSE;

    private static final int BLACKJACK_CHECK_SUM = 21;

    public static Result calculate(Score targetScore, Score oppositeScore) {
        if (targetScore.isGreaterThan(oppositeScore)) {
            return WIN;
        }
        if (targetScore.isLessThan(oppositeScore)) {
            return LOSE;
        }
        return DRAW;
    }

    private static int calculateFinalScore(int sum) {
        if (sum > BLACKJACK_CHECK_SUM) {
            sum = 0;
        }
        return sum;
    }
}
