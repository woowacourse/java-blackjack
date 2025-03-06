public enum Result {
    DRAW, WIN, LOSE;

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
}
