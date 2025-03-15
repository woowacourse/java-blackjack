package ScoreResult;

public enum BattleResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String name;

    BattleResult(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
