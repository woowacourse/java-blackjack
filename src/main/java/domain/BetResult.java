package domain;

public class BetResult {
    private final Long betAmount;
    private final GameResult gameResult;

    private BetResult(Long betAmount, GameResult gameResult) {
        this.betAmount = betAmount;
        this.gameResult = gameResult;
    }

    public static BetResult withBetAmount(Long betAmount){
        return new BetResult(betAmount, null);
    }

    public BetResult withGameResult(GameResult gameResult) {
        return new BetResult(betAmount, gameResult);
    }

    public Long getBetAmount() {
        return betAmount;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}
