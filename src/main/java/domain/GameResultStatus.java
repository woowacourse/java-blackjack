package domain;

public enum GameResultStatus {
    WIN,
    DRAW,
    LOSE;

    public boolean isEqualTo(GameResultStatus gameResultStatus) {
        return gameResultStatus == this;
    }
}
