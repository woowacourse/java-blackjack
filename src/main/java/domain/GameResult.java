package domain;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String title;

    GameResult(String title) {
        this.title = title;
    }

    public static GameResult from(Score playerScore, Score dealerScore) {
        return DRAW;
    }
}
