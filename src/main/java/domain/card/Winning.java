package domain.card;

public enum Winning {
    LOSE("패"),
    PUSH("무"),
    WIN("승");

    private static final int BLACK_JACK_SCORE = 21;

    private final String name;

    Winning(String name) {
        this.name = name;
    }

    public static Winning comparePlayerWithDealer(final int playerScore, final int dealerScore) {
        if (isBust(playerScore) || ((playerScore < dealerScore) && !isBust(dealerScore))) {
            return LOSE;
        }
        if (playerScore == dealerScore) {
            return PUSH;
        }
        return WIN;
    }

    private static boolean isBust(int score) {
        return score > BLACK_JACK_SCORE;
    }

    public String getName() {
        return this.name;
    }
}
