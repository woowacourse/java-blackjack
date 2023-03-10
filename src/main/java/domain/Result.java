package domain;

public enum Result {

    DRAW("무"),
    WIN("승"),
    LOSE("패");

    private final String message;

    Result(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }

}
