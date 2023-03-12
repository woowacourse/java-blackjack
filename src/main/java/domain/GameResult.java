package domain;

public enum GameResult {
    WIN, LOSE, DRAW;

    public GameResult reverse() {
        if (this.equals(WIN)) {
            return LOSE;
        }
        if (this.equals(LOSE)) {
            return WIN;
        }
        return DRAW;
    }
}
