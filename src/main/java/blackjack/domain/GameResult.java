package blackjack.domain;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private static final int BLACKJACK_MAX_SCORE = 21;
    private final String value;

    GameResult(String value) {
        this.value = value;
    }

    public static GameResult findPlayerResult(int playerScore, int dealerScore) {
        if (playerScore > BLACKJACK_MAX_SCORE && dealerScore > BLACKJACK_MAX_SCORE) {
            return DRAW;
        }
        if (playerScore > BLACKJACK_MAX_SCORE) {
            return LOSE;
        }
        if (dealerScore > BLACKJACK_MAX_SCORE) {
            return WIN;
        }
        return compareScore(playerScore, dealerScore);
    }

    private static GameResult compareScore(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }

    public static GameResult findDealerResult(GameResult playerResult) {
        if (playerResult == WIN) {
            return LOSE;
        }
        if (playerResult == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getValue() {
        return value;
    }
}
