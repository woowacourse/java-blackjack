package domain;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String state;

    Result(String state) {
        this.state = state;
    }

    public static Result judge(Hand dealerCards, Hand playersCards) {
        int dealerScore = dealerCards.calculateTotalScore();
        int playerScore = playersCards.calculateTotalScore();

        boolean dealerBust = dealerCards.isBust();
        boolean playersBust = playersCards.isBust();

        if (dealerBust && playersBust) {
            return DRAW;
        }

        if (dealerScore == playerScore) {
            return DRAW;
        }

        if (dealerBust) {
            return LOSE;
        }

        if (playersBust) {
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
