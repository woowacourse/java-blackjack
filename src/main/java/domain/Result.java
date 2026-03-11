package domain;


import static domain.Rank.BLACKJACK_MAX_SCORE;

public enum Result {
    WIN("승"), DRAW("무"), LOSE("패");

    private final String description;

    Result(String description) {
        this.description = description;
    }

    public static Result judge(Score playerSum, Score dealerSum) {
        if (isPlayerWin(playerSum, dealerSum)) {
            return WIN;
        }
        if (isPlayerLose(playerSum, dealerSum)) {
            return LOSE;
        }
        return DRAW;
    }

    public String getDescription() {
        return description;
    }

    private static boolean isPlayerWin(Score playerSum, Score dealerSum) {
        return playerSum.isLessThanOrEqualTo(BLACKJACK_MAX_SCORE) && playerSum.isGreaterThan(dealerSum);
    }

    private static boolean isPlayerLose(Score playerSum, Score dealerSum) {
        return (playerSum.isLessThan(dealerSum) && dealerSum.isLessThanOrEqualTo(BLACKJACK_MAX_SCORE) || (playerSum.isGreaterThan(BLACKJACK_MAX_SCORE))
                && dealerSum.isLessThanOrEqualTo(BLACKJACK_MAX_SCORE));
    }
}
