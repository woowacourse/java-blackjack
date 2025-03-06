package domain;

public enum GameResult {

    WIN,
    LOSE,
    DRAW;

    GameResult() {
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
}
