package blackjack.domain;

import static blackjack.domain.card.Cards.BLACKJACK_NUMBER;

public enum ResultStatus {

    WIN,
    LOSE,
    DRAW;

    public static ResultStatus calculateResultStatus(final int sum, final int comparedSum) {
        if (sum <= BLACKJACK_NUMBER) {
            return calculateResultStatusUnder21(sum, comparedSum);
        }
        return LOSE;
    }

    private static ResultStatus calculateResultStatusUnder21(final int sum, final int comparedSum) {
        if (comparedSum <= BLACKJACK_NUMBER) {
            return calculateResultStatusBothUnder21(sum, comparedSum);
        }
        return WIN;
    }

    private static ResultStatus calculateResultStatusBothUnder21(final int sum, final int comparedSum) {
        if (sum > comparedSum) {
            return WIN;
        }
        if (sum == comparedSum) {
            return DRAW;
        }
        return LOSE;
    }
}
