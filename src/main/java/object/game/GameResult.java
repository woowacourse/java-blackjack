package object.game;

public enum GameResult {
    BLACKJACK_WIN("블랙잭", 2.5),
    WIN("승", 2.0),
    LOSE("패", 0.0),
    DRAW("무", 1.0)
    ;

    private final String name;
    private final double betRate;

    GameResult(final String name, final double betRate) {
        this.name = name;
        this.betRate = betRate;
    }

    public String getName() {
        return name;
    }

    public double getBetRate() {
        return betRate;
    }

    public static GameResult getGameResultOfPlayer(Score playerScore, Score dealerScore) {
        if (playerScore.isBlackJack() && !dealerScore.isBlackJack()) {
            return BLACKJACK_WIN;
        }
        if (playerScore.isBust()) {
            return LOSE;
        }
        if (dealerScore.isBust()) {
            return WIN;
        }
        if (playerScore.getScore() > dealerScore.getScore()) {
            return WIN;
        }
        if (playerScore.getScore() < dealerScore.getScore()) {
            return LOSE;
        }

        return DRAW;
    }

    public static GameResult getOppositeGameResult(GameResult gameResult) {
        if (gameResult == WIN || gameResult == BLACKJACK_WIN) {
            return LOSE;
        }
        if (gameResult == LOSE) {
            return WIN;
        }

        return gameResult;
    }
}
