package domain;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String state;

    Result(String state) {
        this.state = state;
    }

    public static Result judge(Cards dealerCards, Cards playersCards) {
        int dealerScore = dealerCards.calculateTotalPoint();
        int playerScore = playersCards.calculateTotalPoint();

        boolean dealerBurst = dealerCards.isBurst();
        boolean playersBurst = playersCards.isBurst();

        if (dealerBurst && playersBurst) {
            return DRAW;
        }

        if (dealerScore == playerScore) {
            return DRAW;
        }

        if (dealerBurst) {
            return LOSE;
        }

        if (playersBurst) {
            return WIN;
        }

        if (dealerScore > playerScore) {
            return WIN;
        }

        return LOSE;
    }

    public String getState() {
        return state;
    }
}
