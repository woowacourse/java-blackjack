package blackjack.domain;

import static blackjack.domain.card.Cards.BLACKJACK_NUMBER;

public enum ResultStatus {

    WIN,
    LOSE,
    DRAW;

    public static ResultStatus calculateResultStatus(final int score, final int comparedScore) {
        if (score <= BLACKJACK_NUMBER) {
            return calculateResultStatusUnder21(score, comparedScore);
        }
        return LOSE;
    }

    private static ResultStatus calculateResultStatusUnder21(final int score, final int comparedScore) {
        if (comparedScore <= BLACKJACK_NUMBER) {
            return calculateResultStatusBothUnder21(score, comparedScore);
        }
        return WIN;
    }

    private static ResultStatus calculateResultStatusBothUnder21(final int score, final int comparedScore) {
        if (score > comparedScore) {
            return WIN;
        }
        if (score == comparedScore) {
            return DRAW;
        }
        return LOSE;
    }
}
