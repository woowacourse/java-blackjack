package domain;

public enum GameResult {
    WIN,
    LOSE,
    DRAW;

    public static GameResult of(Participant player, Participant dealer) {
        if (player.getState() == ScoreState.BUST) {
            return GameResult.LOSE;
        }
        if (dealer.getState() == ScoreState.BUST) {
            return GameResult.WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return GameResult.DRAW;
        }
        if (player.getScore() > dealer.getScore()) {
            return GameResult.WIN;
        }
        return GameResult.LOSE;
    }
}
