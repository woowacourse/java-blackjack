package domain.result;

public enum WinningStatus {

    WIN("승"),
    LOSS("패"),
    DRAW("무"),
    BLCAKJACK("블랙잭");

    private final String result;

    WinningStatus(String result) {
        this.result = result;
    }

    public String displayName() {
        return result;
    }

    public WinningStatus reverseResult() {
        if (this == WIN) {
            return LOSS;
        }

        if (this == LOSS) {
            return WIN;
        }

        return this;
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isDraw() {
        return this == DRAW;
    }

    public boolean isBlackjack() {
        return this == BLCAKJACK;
    }

}
