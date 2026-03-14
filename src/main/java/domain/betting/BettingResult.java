package domain.betting;

public enum BettingResult {

    BLACK_JACK_PUSH(1.0, GameResult.DRAW),
    BLACK_JACK(1.5, GameResult.WIN),
    PLAYER_WIN(1.0, GameResult.WIN),
    PLAYER_LOSE(-1.0, GameResult.LOSS),
    ;

    private final double bettingRate;
    private final GameResult gameResult;

    BettingResult(double bettingRate, GameResult gameResult) {
        this.bettingRate = bettingRate;
        this.gameResult = gameResult;
    }

    public double bettingRate() {
        return bettingRate;
    }

    public GameResult gameResult() {
        return gameResult;
    }

}
