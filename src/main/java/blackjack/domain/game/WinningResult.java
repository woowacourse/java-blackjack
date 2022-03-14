package blackjack.domain.game;

public enum WinningResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    NONE("NONE");

    private final String result;

    WinningResult(String result) {
        this.result = result;
    }

    public WinningResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }

    public String getResult() {
        return result;
    }
}
