package domain;

public enum MatchResult {
    WIN,
    DRAW,
    LOSE;

    private static final int BLACKJACK_NUMBER = 21;

    public static MatchResult calculateWinner(int dealerSum, int playerSum) {
        if (dealerSum > playerSum || playerSum > BLACKJACK_NUMBER) {
            return LOSE;
        }
        if (dealerSum < playerSum) {
            return WIN;
        }
        return DRAW;
    }
}