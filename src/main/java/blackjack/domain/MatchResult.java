package blackjack.domain;

public enum MatchResult {

    WIN("승") {
        @Override
        boolean match(int playerScore, int dealerScore) {
            return (playerScore <= MAX_SCORE && dealerScore > MAX_SCORE) || ((playerScore > dealerScore) && playerScore <= MAX_SCORE);
        }
    },
    LOSE("패") {
        @Override
        boolean match(int playerScore, int dealerScore) {
            return (playerScore > MAX_SCORE && dealerScore <= MAX_SCORE) || ((playerScore < dealerScore) && dealerScore <= MAX_SCORE);
        }
    },
    DRAW("무") {
        @Override
        boolean match(int playerScore, int dealerScore) {
            return (playerScore > MAX_SCORE && dealerScore > MAX_SCORE) || (playerScore == dealerScore);
        }
    };

    public static final int MAX_SCORE = 21;

    private final String result;

    MatchResult(String result) {
        this.result = result;
    }

    abstract boolean match(int playerScore, int dealerScore);

    public static MatchResult getPlayerMatchResult(int playerScore, int dealerScore) {
        for (MatchResult matchResult : values()) {
            if (matchResult.match(playerScore, dealerScore)) {
                return matchResult;
            }
        }
        throw new IllegalArgumentException();
    }

    public static MatchResult getDealerMatchResultByPlayer(MatchResult matchResult) {
        if (matchResult.equals(MatchResult.WIN)) {
            return MatchResult.LOSE;
        }
        if (matchResult.equals(MatchResult.LOSE)) {
            return MatchResult.WIN;
        }
        return MatchResult.DRAW;
    }

    public String getResult() {
        return result;
    }
}
