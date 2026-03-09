package blackjack.domain.result;

import blackjack.domain.hand.Score;

public enum GameResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String displayName;

    GameResult(final String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static GameResult of(final Score playerScore, final Score dealerScore) {
        if (playerScore.isBust()) {
            return LOSE;
        }
        if (dealerScore.isBust()) {
            return WIN;
        }
        return compare(playerScore, dealerScore);
    }

    private static GameResult compare(final Score playerScore, final Score dealerScore) {
        if (playerScore.isGreaterThan(dealerScore)) {
            return WIN;
        }
        if (playerScore.isLessThan(dealerScore)) {
            return LOSE;
        }
        return DRAW;
    }

    public GameResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
