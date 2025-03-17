package ScoreResult;

public enum BattleResult {
    WIN,
    LOSE,
    DRAW;

    public BattleResult reverse() {
        if (this == WIN) return LOSE;
        if (this == LOSE) return WIN;
        return DRAW;
    }

}
