package blackjack;

public enum Outcome {
    WIN, LOSE, DRAW;

    private static final int BLACK_JACK_NUMBER = 21;

    public static Outcome match(int dealerTotal, int playerTotal) {
        if (dealerTotal > BLACK_JACK_NUMBER && playerTotal > BLACK_JACK_NUMBER) {
            return DRAW;
        }
        if (dealerTotal > BLACK_JACK_NUMBER) {
            return WIN;
        }
        if (playerTotal > BLACK_JACK_NUMBER) {
            return LOSE;
        }
        if (dealerTotal < playerTotal) {
            return WIN;
        }
        if (dealerTotal > playerTotal) {
            return LOSE;
        }
        return DRAW;
    }
}
