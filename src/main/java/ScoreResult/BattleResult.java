package ScoreResult;

public enum BattleResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String name;

    BattleResult(final String name) {
        this.name = name;
    }

    public BattleResult reverse() {
        if (this == WIN) return LOSE;
        if (this == LOSE) return WIN;
        return DRAW;
    }

    public String getName() {
        return name;
    }
}
