package blackjack.domain;

public enum ResultStatus {

    WIN,
    LOSE,
    DRAW;

    public static ResultStatus calculateResultStatus(final int sum, final int comparedSum) {
        if (sum > comparedSum) {
            return WIN;
        }
        if (sum == comparedSum) {
            return DRAW;
        }
        return LOSE;
    }
}
