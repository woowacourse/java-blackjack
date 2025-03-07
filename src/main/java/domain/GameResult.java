package domain;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    GameResult(final String description) {
        this.description = description;
    }

    public static GameResult findByScores(int score, int compareScore) {
        if (score > compareScore) {
            return WIN;
        }
        if (score < compareScore) {
            return LOSE;
        }
        return DRAW;
    }

    public String getDescription() {
        return description;
    }
}
