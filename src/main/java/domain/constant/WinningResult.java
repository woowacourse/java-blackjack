package domain.constant;

public enum WinningResult {
    WIN("승"), DRAW("무"), LOSE("패"),
    ;

    private final String message;

    WinningResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
