package model.dto;

public enum Victory {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    Victory(String value) {
        this.value = value;
    }

    public static Victory of(int standard, int comparisonTarget) {
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
