package domain.game;

public enum MatchResult {
    WIN("승"),
    LOSE("패");

    private final String value;

    MatchResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
