package domain.constant;

public enum WinDrawLose {
    WIN("승"), DRAW("무"), LOSE("패"),
    ;

    private final String message;

    WinDrawLose(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
