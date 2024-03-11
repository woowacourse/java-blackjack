package blackjack.domain.gamer;

public enum GameResult {

    WIN,
    LOSE;

    public static boolean isLose(GameResult gameResult) {
        return LOSE == gameResult;
    }
}
