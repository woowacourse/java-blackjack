package domain.game;

public enum Winning {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    public static final int BLACK_JACK = 21;

    private final String name;

    Winning(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Winning reverse(Winning winning) {
        if (winning == WIN) {
            return LOSE;
        }
        if (winning == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public static Winning determineForPlayer(int playerScore, int dealerScore) {
        if (playerScore > BLACK_JACK) {
            return LOSE;
        }
        if (dealerScore > BLACK_JACK) {
            return WIN;
        }

        return determinePlayerWinning(playerScore, dealerScore);
    }

    private static Winning determinePlayerWinning(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }
}
