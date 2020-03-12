package domain;

public enum WinningResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    private final String result;

    WinningResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public WinningResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
