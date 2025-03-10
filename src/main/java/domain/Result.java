package domain;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String state;

    Result(String state) {
        this.state = state;
    }

    public static Result judge(Hand dealerHand, Hand playerHand) {
        if (bothPlayersBust(dealerHand, playerHand)) {
            return DRAW;
        }
        if (dealerHand.isBust()) {
            return LOSE;
        }
        if (playerHand.isBust()) {
            return WIN;
        }
        return compareScores(dealerHand.calculateTotalScore(), playerHand.calculateTotalScore());
    }

    private static boolean bothPlayersBust(Hand dealerHand, Hand playerHand) {
        return dealerHand.isBust() && playerHand.isBust();
    }

    private static Result compareScores(int dealerScore, int playerScore) {
        if (dealerScore == playerScore) {
            return DRAW;
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
