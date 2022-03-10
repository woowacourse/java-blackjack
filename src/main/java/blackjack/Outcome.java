package blackjack;

public enum Outcome {
    WIN, LOSE, DRAW;

    public static Outcome match(int dealerTotal, int playerTotal) {
        if (dealerTotal < playerTotal) {
            return WIN;
        }
        if (dealerTotal > playerTotal) {
            return LOSE;
        }
        return DRAW;
    }
}
