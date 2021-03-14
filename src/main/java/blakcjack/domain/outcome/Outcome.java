package blakcjack.domain.outcome;

public enum Outcome {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    Outcome(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean isWin() {
        return equals(WIN);
    }
}
