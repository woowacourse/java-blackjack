package domain;

public enum GameResult {
    WIN("승"),
    LOSE("패");

    private final String value;

    GameResult(String value) {
        this.value = value;
    }

    public static GameResult of(Participant player, Participant dealer) {
        if (player.getState() == ScoreState.BUST) {
            return GameResult.LOSE;
        }
        if (dealer.getState() == ScoreState.BUST) {
            return GameResult.WIN;
        }
        if (player.getScore() >= dealer.getScore()) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }

    public boolean isWin() {
        return this == WIN;
    }

    public String getValue() {
        return value;
    }
}
