package domain.participant;

public enum Result {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String message;

    Result(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
