package model.result;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String name;

    GameResult(final String name) {
        this.name = name;
    }

    public static GameResult getOppositeResult(final GameResult gameResult) {
        if (WIN.equals(gameResult)) {
            return LOSE;
        }
        if (LOSE.equals(gameResult)) {
            return WIN;
        }
        return DRAW;
    }

    public String getName() {
        return name;
    }
}
