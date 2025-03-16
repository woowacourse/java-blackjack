package domain;

public enum GameResultStatus {
    WIN,
    DRAW,
    LOSE,
    IN_PROGRESS;

    public boolean isEqualTo(GameResultStatus gameResultStatus) {
        return gameResultStatus == this;
    }
}
