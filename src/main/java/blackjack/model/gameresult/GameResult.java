package blackjack.model.gameresult;

public enum GameResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String format;

    GameResult(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}