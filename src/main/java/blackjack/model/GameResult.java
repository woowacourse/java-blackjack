package blackjack.model;

public enum GameResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String format;

    GameResult(String format) {
        this.format = format;
    }
}
