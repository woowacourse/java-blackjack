package blackjack.domain;

public enum MatchResultType {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private static final int BLACK_JACK_SCORE = 21;

    private final String status;

    MatchResultType(String status) {
        this.status = status;
    }

    public static MatchResultType getStatus(int dealerScore, int playerScore) {
        if (isDealerBusted(dealerScore) || (playerScore > dealerScore && isPlayerNotBusted(playerScore))) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }

    private static boolean isDealerBusted(int dealerScore) {
        return dealerScore > BLACK_JACK_SCORE;
    }

    private static boolean isPlayerNotBusted(int playerScore) {
        return playerScore <= BLACK_JACK_SCORE;
    }

    @Override
    public String toString() {
        return status;
    }
}
