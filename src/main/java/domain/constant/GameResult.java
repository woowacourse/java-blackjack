package domain.constant;

public enum GameResult {
    WIN("승"), DRAW("무"), LOSE("패"),
    ;

    private final String message;

    GameResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
