package domain;

public enum PlayerGameResult {
    LOSE("패"),
    DRAW("무"),
    WIN("승");

    private static final int BLACKJACK = 21;

    private final String name;

    PlayerGameResult(final String name) {
        this.name = name;
    }

    public static PlayerGameResult of(int playerScore, int dealerScore) {
        if (playerScore > BLACKJACK) {
            return LOSE;
        }
        if (dealerScore > BLACKJACK || playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }

        return LOSE;
    }

    public String getName() {
        return name;
    }
}
