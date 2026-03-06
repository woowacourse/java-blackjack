package domain;

public enum MatchResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    MatchResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public MatchResult exchange() {
        if (this == MatchResult.WIN) return MatchResult.LOSE;
        if (this == MatchResult.LOSE) return MatchResult.WIN;
        return MatchResult.DRAW;
    }
}
