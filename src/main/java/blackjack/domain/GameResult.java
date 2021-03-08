package blackjack.domain;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    TIE("무");

    private static final int MAX_SCORE_NUMBER = 21;

    private final String name;

    GameResult(final String name) {
        this.name = name;
    }

    public static GameResult judgeHand(final int dealerScore, final int playerScore) {
        if (playerScore > MAX_SCORE_NUMBER || (dealerScore <= MAX_SCORE_NUMBER && dealerScore > playerScore)) {
            return LOSE;
        }
        if (dealerScore < playerScore || dealerScore > MAX_SCORE_NUMBER) {
            return WIN;
        }
        return TIE;
    }

    public static GameResult reverseResult(final GameResult gameResult) {
        if (gameResult == WIN) {
            return LOSE;
        }
        if (gameResult == LOSE) {
            return WIN;
        }
        return TIE;
    }

    public String getName() {
        return name;
    }
}
