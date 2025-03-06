package domain;

public enum MatchResult {
    WIN,
    DRAW,
    LOSE;

    public static MatchResult calculateWinner(int dealerSum, int playerSum) {
        if (dealerSum > playerSum) {
            return LOSE;
        }
        if (dealerSum < playerSum) {
            return WIN;
        }
        return DRAW;
    }
}
