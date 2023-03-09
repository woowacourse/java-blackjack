package blackjack.domain.game;

import blackjack.domain.participant.Score;

public enum Result {
    WIN,
    DRAW,
    LOSE;

    private static final Score BLACKJACK_SCORE = new Score(21);

    public static Result calculatePlayerResult(Score playerScore, Score dealerScore) {
        if (isBust(playerScore)) {
            return LOSE;
        }
        if (isBust(dealerScore)) {
            return WIN;
        }
        return calculateResultByScore(playerScore, dealerScore);
    }

    private static Result calculateResultByScore(Score targetScore, Score oppositeScore) {
        if (targetScore.isGreaterThan(oppositeScore)) {
            return WIN;
        }
        if (targetScore.isLessThan(oppositeScore)) {
            return LOSE;
        }
        return DRAW;
    }

    private static boolean isBust(Score score) {
        return score.isGreaterThan(BLACKJACK_SCORE);
    }

    public static Result oppositeResult(Result result) {
        if (result.equals(WIN)) {
            return LOSE;
        }
        if (result.equals(LOSE)) {
            return WIN;
        }
        return DRAW;
    }
}
