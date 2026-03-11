package blackjack.domain.participant;

public enum GameResult {
    WIN, DRAW, LOSE;

    public static GameResult reverse(GameResult gameResult) {
        if (gameResult == GameResult.WIN) {
            return GameResult.LOSE;
        }
        if (gameResult == GameResult.LOSE) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }
}
