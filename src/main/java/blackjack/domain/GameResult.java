package blackjack.domain;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String value;

    GameResult(String value) {
        this.value = value;
    }

    public static GameResult findPlayerResult(int playerScore, int dealerScore) {
        if (playerScore > 21 && dealerScore > 21) {
            return DRAW;
        }
        if (playerScore > 21) {
            return LOSE;
        }
        if (dealerScore > 21) {
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

    public static GameResult findDealerResult(GameResult dealerResult) {
        if (dealerResult == WIN) {
            return LOSE;
        }
        if (dealerResult == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getValue() {
        return value;
    }
}
