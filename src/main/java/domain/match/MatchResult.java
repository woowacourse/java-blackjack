package domain.match;

public enum MatchResult {

    DEALER_WIN("승"),
    DRAW("무"),
    DEALER_LOSE("패"),
    DEALER_BLACKJACK_LOSE("블랙잭 패배");

    private final String state;

    MatchResult(String state) {
        this.state = state;
    }

    public static MatchResult judge(int dealerScore, int playerScore) {
        return compareScores(dealerScore, playerScore);
    }

    private static MatchResult compareScores(int dealerScore, int playerScore) {
        if (dealerScore == playerScore) {
            return DRAW;
        }
        if (dealerScore > playerScore) {
            return DEALER_WIN;
        }
        return DEALER_LOSE;
    }

    public MatchResult reverse() {
        if (this == DEALER_WIN) {
            return DEALER_LOSE;
        }
        if (this == DEALER_LOSE) {
            return DEALER_WIN;
        }
        return DRAW;
    }

    public String getState() {
        return state;
    }
}
