package domain.betting;

import domain.analyzer.GameResult;

// THINK 이 ENUM들을 그냥 BettingState로..? 상태패턴...?
public enum BettingResult {

    BLACK_JACK_PUSH(new BettingRate(1.0), GameResult.DRAW),
    BLACK_JACK(new BettingRate(1.5), GameResult.WIN),
    DOUBLE_BUST(new BettingRate(1.0), GameResult.WIN),
    DEALER_BUST(new BettingRate(1.0), GameResult.WIN),
    PLAYER_BUST(new BettingRate(-1.0), GameResult.LOSS),
    COMPARE_WIN(new BettingRate(1.0), GameResult.WIN),
    COMPARE_LOSE(new BettingRate(-1.0), GameResult.LOSS),
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
        if (this == COMPARE_WIN) {
            return COMPARE_LOSE;
        }
        return COMPARE_WIN;
    }

}
