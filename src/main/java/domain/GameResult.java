package domain;

public enum GameResult {
    LOSE("패"),
    PUSH("무"),
    WIN("승");

    private static final int BLACK_JACK_SCORE = 21;

    private final String name;

    GameResult(String name) {
        this.name = name;
    }

    public static GameResult comparePlayerWithDealer(final int playerScore, final int dealerScore) {
        if ((playerScore == dealerScore) || (playerScore > BLACK_JACK_SCORE && dealerScore > BLACK_JACK_SCORE)) {
            return PUSH;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return WIN;
    }

    public String getName() {
        return this.name;
    }
}
