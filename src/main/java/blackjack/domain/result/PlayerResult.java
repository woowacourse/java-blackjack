package blackjack.domain.result;

public enum PlayerResult {

    WIN("승"),
    DRAW("무"),
    LOSS("패");

    private final String name;

    PlayerResult(final String name) {
        this.name = name;
    }

    public static PlayerResult calculateResult(final int score, final int otherScore) {
        if (score > otherScore) {
            return WIN;
        }
        if (score == otherScore) {
            return DRAW;
        }
        return LOSS;
    }

    public String getName() {
        return name;
    }
}
