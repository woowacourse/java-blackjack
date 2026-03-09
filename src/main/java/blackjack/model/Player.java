package blackjack.model;


public class Player extends User {
    private GameResult gameResult;

    public Player(String name) {
        super(name);
        this.gameResult = GameResult.DRAW;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void mark(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    @Override
    public boolean isHitAvailable() {
        return totalScore() < Constant.BLACKJACK_SCORE;
    }
}
