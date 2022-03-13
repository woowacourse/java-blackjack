package blackjack.domain.game;

public enum WinningResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    NONE("NONE");

    private String result;

    WinningResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
