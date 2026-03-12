package domain.result;

public enum GameResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String description;

    GameResult(final String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }
}
