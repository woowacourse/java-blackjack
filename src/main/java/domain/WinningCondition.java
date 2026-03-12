package domain;

public enum WinningCondition {
    WIN("승"), DRAW("무"), LOSE("패"), BLACK_JACK(null);

    private final String message;

    WinningCondition(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
