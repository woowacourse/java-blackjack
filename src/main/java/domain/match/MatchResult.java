package domain.match;

public enum MatchResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    BLACKJACK_LOSE("블랙잭 패배");

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
            return WIN;
        }
        return LOSE;
    }

    public MatchResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getState() {
        return state;
    }
}
