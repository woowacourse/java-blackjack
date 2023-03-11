package blackjack.domain;

public enum ResultType {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String message;

    ResultType(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
