package blackjack.domain;

import static blackjack.controller.BlackJackGame.BLACKJACK_NUMBER;

public enum MatchResult {
    WIN("승") {
        @Override
        boolean match(int playerScore, int dealerScore) {
            return (playerScore <= BLACKJACK_NUMBER && dealerScore > BLACKJACK_NUMBER) || ((playerScore > dealerScore) && playerScore <= BLACKJACK_NUMBER);
        }
    },
    LOSE("패") {
        @Override
        boolean match(int playerScore, int dealerScore) {
            return (playerScore > BLACKJACK_NUMBER && dealerScore <= BLACKJACK_NUMBER) || ((playerScore < dealerScore) && dealerScore <= BLACKJACK_NUMBER);
        }
    },
    DRAW("무") {
        @Override
        boolean match(int playerScore, int dealerScore) {
            return (playerScore > BLACKJACK_NUMBER && dealerScore > BLACKJACK_NUMBER) || (playerScore == dealerScore);
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
