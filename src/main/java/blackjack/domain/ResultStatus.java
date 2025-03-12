package blackjack.domain;

import static blackjack.domain.card.Hand.BURST_THRESHOLD;

public enum ResultStatus {

    WIN,
    LOSE,
    PUSH;

    public static ResultStatus calculateResultStatus(final int score, final int comparedScore) {
        if (score <= BURST_THRESHOLD) {
            return calculateResultStatusUnderBlackjackNumber(score, comparedScore);
        }
        return WIN;
    }

    private static ResultStatus calculateResultStatusUnderBlackjackNumber(final int score, final int comparedScore) {
        if (comparedScore <= BURST_THRESHOLD) {
            return calculateResultStatusBothUnderBlackjackNumber(score, comparedScore);
        }
        return LOSE;
    }

    private static ResultStatus calculateResultStatusBothUnderBlackjackNumber(final int score,
                                                                              final int comparedScore) {
        if (score > comparedScore) {
            return LOSE;
        }
        if (score == comparedScore) {
            return PUSH;
        }
        return WIN;
    }

    public ResultStatus makeOppositeResult() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return PUSH;
    }
}
