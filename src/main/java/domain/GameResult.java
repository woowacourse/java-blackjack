package domain;

public enum GameResult {
    WIN,
    LOSE,
    DRAW;

    public GameResult getReverse() {
        return switch (this) {
            case LOSE -> WIN;
            case WIN -> LOSE;
            case DRAW -> DRAW;
        };
    }
}
