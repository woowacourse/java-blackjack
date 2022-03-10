package blackjack.domain;

public enum Outcome {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private static final int BLACK_JACK_NUMBER = 21;

    private String value;

    Outcome(String value) {
        this.value = value;
    }

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

    public String getValue() {
        return this.value;
    }
}
