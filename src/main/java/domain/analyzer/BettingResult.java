package domain.analyzer;

import domain.betting.BettingRate;

// THINK 이 ENUM들을 그냥 BettingState로..? 상태패턴...?
public enum BettingResult {

    BLACK_JACK_PUSH(new BettingRate(1.0), GameResult.DRAW),
    BLACK_JACK(new BettingRate(1.5), GameResult.WIN),
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

}
