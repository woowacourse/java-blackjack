package domain;

public enum BlackjackMatchResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String state;

    BlackjackMatchResult(String state) {
        this.state = state;
    }

    public static BlackjackMatchResult judge(Hand dealerHand, Hand playersHand) {
        int dealerScore = dealerHand.calculateTotalPoint();
        int playerScore = playersHand.calculateTotalPoint();

        boolean dealerBurst = dealerHand.isBurst();
        boolean playersBurst = playersHand.isBurst();

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
