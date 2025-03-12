package blackjack.domain;

import static blackjack.domain.card.Hand.BLACKJACK_NUMBER;

public enum ResultStatus {

    WIN,
    LOSE,
    PUSH;

    public static ResultStatus calculateResultStatus(final int score, final int comparedScore) {
        if (score <= BLACKJACK_NUMBER) {
            return calculateResultStatusUnderBlackjackNumber(score, comparedScore);
        }
        return LOSE;
    }

    private static ResultStatus calculateResultStatusUnderBlackjackNumber(final int score, final int comparedScore) {
        if (comparedScore <= BLACKJACK_NUMBER) {
            return calculateResultStatusBothUnderBlackjackNumber(score, comparedScore);
        }
        return WIN;
    }

    private static ResultStatus calculateResultStatusBothUnderBlackjackNumber(final int score, final int comparedScore) {
        if (score > comparedScore) {
            return WIN;
        }
        if (score == comparedScore) {
            return PUSH;
        }
        return LOSE;
    }
}
