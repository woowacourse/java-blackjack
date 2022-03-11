package blackjack.domain.result;

public enum WinningResult {

    WIN("승"),
    LOSS("패");

    private final String name;

    WinningResult(final String name) {
        this.name = name;
    }

    public static WinningResult calculateResult(final int score, final int otherScore) {
        if (score > otherScore) {
            return WIN;
        }
        return LOSS;
    }

    public String getName() {
        return name;
    }
}
