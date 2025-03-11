package domain.blackJack;

public enum MatchResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    MatchResult(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}