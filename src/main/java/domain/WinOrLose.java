package domain;

public enum WinOrLose {

    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    private final String message;

    WinOrLose(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
