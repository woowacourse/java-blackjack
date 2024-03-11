package model.dto;

public enum MatchResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    MatchResult(String value) {
        this.value = value;
    }

    public static MatchResult of(int standard, int comparisonTarget) {
        if (standard < comparisonTarget) {
            return WIN;
        }
        if (standard == comparisonTarget) {
            return DRAW;
        }
        return LOSE;
    }
    public String getValue() {
        return value;
    }
}
