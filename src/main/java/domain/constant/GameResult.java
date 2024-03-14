package domain.constant;

public enum GameResult {
    WIN, LOSE, DRAW;

    public GameResult getReverseResult() {
        if (this == WIN) {
            return LOSE;
        }

        if (this == LOSE) {
            return WIN;
        }

        return DRAW;
    }
}
