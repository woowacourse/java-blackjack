package blackjack.domain.result;

public enum ResultMatcher {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String playerResult;

    ResultMatcher(String playerResult) {
        this.playerResult = playerResult;
    }

    public static ResultMatcher calculateResult(int playerScore, int dealerScore) {
        if (playerScore > 21) {
            playerScore = 0;
        }
        if (dealerScore > 21) {
            dealerScore = 0;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        if (playerScore > dealerScore) {
            return WIN;
        }
        return TIE;
    }

    public static ResultMatcher ofOppositeResult(ResultMatcher resultMatcher) {
        if (resultMatcher == WIN) {
            return LOSE;
        }
        if (resultMatcher == LOSE) {
            return WIN;
        }
        return TIE;
    }

    public String getResult() {
        return this.playerResult;
    }
}
