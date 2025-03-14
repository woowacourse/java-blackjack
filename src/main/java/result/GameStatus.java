package result;

public enum GameStatus {
    WIN,
    DRAW,
    LOSE,
    UNDECIDED;

    public boolean isEqualTo(GameStatus gameStatus) {
        return gameStatus == this;
    }

    public boolean isDecided() {
        return this != UNDECIDED;
    }
}
