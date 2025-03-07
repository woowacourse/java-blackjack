package domain;

public enum GameResult {
    WIN,
    LOSE,
    DRAW;

    public static GameResult judge(Participant self, Dealer other) {
        if (self.isBurst()) {
            return GameResult.LOSE;
        }
        if (self.getCardScore() > other.getCardScore()) {
            return GameResult.WIN;
        }
        if (self.getCardScore() < other.getCardScore()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public GameResult getReverse() {
        return switch (this) {
            case LOSE -> WIN;
            case WIN -> LOSE;
            case DRAW -> DRAW;
        };
    }
}
