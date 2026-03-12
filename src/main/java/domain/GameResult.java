package domain;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private final String message;

    GameResult(String message) {
        this.message = message;
    }
}
