package domain.betting;

import domain.analyzer.GameResult;

public enum BettingResult {

    BLACK_JACK_PUSH(new BettingRate(1.0), GameResult.DRAW),
    BLACK_JACK(new BettingRate(1.5), GameResult.WIN),
    PLAYER_WIN(new BettingRate(1.0), GameResult.WIN),
    PLAYER_LOSE(new BettingRate(-1.0), GameResult.LOSS),
    ;

    private final BettingRate bettingRate;
    private final GameResult gameResult;

    BettingResult(BettingRate bettingRate, GameResult gameResult) {
        this.bettingRate = bettingRate;
        this.gameResult = gameResult;
    }

    public BettingRate bettingRate() {
        return bettingRate;
    }

    public GameResult gameResult() {
        return gameResult;
    }

    public BettingResult reverseResult() {
        if (this == PLAYER_WIN) {
            return PLAYER_LOSE;
        }
        return PLAYER_WIN;
    }

}
