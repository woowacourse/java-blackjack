package blackjack.domain;

import static blackjack.domain.Score.maxScore;

public enum MatchResult {

    WIN("승") {
        @Override
        boolean match(int playerScore, int dealerScore) {
            return (playerScore <= maxScore && dealerScore > maxScore) || ((playerScore > dealerScore) && playerScore <= maxScore);
        }
    },
    LOSE("패") {
        @Override
        boolean match(int playerScore, int dealerScore) {
            return (playerScore > maxScore && dealerScore <= maxScore) || ((playerScore < dealerScore) && dealerScore <= maxScore);
        }
    },
    DRAW("무") {
        @Override
        boolean match(int playerScore, int dealerScore) {
            return (playerScore > maxScore && dealerScore > maxScore) || (playerScore == dealerScore);
        }
    };

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
