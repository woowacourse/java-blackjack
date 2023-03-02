package domain.player;

public enum GameResult {
    WIN, LOSE, DRAW;

    public boolean isWinning() {
        return this == WIN;
    }

    public boolean isLose() {
        return this == LOSE;
    }

    public boolean isDraw() {
        return this == DRAW;
    }
}
