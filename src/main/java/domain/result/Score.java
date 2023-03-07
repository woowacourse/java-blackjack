package domain.result;

public enum Score {
    WIN("승 "),
    DRAW("무 "),
    LOSE("패");

    private final String value;

    Score(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
