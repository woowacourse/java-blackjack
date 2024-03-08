package domain;

public enum GameResultStatus {

    WIN("승"), LOSE("패"), PUSH("무");

    private final String value;

    GameResultStatus(String value) {
        this.value = value;
    }

    public GameResultStatus opposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return PUSH;
    }

    public String getValue() {
        return value;
    }
}
