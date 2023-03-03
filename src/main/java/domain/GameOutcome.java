package domain;

public enum GameOutcome {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String value;

    GameOutcome(String value) {
        this.value = value;
    }

    public static GameOutcome of(int criteria, int comparison) {
        if (criteria > 21) {
            return LOSE;
        }
        if (comparison > 21) {
            return WIN;
        }
        if (criteria > comparison) {
            return WIN;
        }
        if (criteria < comparison) {
            return LOSE;
        }
        return DRAW;
    }

    public GameOutcome oppositeOutcome() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String value() {
        return value;
    }
}
