package domain;

public enum GameResult {

    WIN,
    LOSE,
    DRAW;

    public static GameResult judge(Participant self, Participant other) {
        if (self.isBurst()) {
            return GameResult.LOSE;
        }
        if (self.getResult() > other.getResult()) {
            return GameResult.WIN;
        }
        if (self.getResult() < other.getResult()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
