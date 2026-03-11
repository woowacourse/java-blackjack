package domain.analyzer;

import domain.betting.BettingRate;

// THINK 이 ENUM들을 그냥 BettingState로..? 상태패턴...?
public enum BettingResult {

    BLACK_JACK_PUSH(new BettingRate(1.0), GameResult.DRAW, 1),
    BLACK_JACK(new BettingRate(1.5), GameResult.WIN, 2),
    DOUBLE_BUST(new BettingRate(1.0), GameResult.WIN, 3),
    BUST(new BettingRate(-1.0), GameResult.LOSS, 4),
    COMPARE_WIN(new BettingRate(1.0), GameResult.WIN, 5),
    COMPARE_LOSE(new BettingRate(-1.0), GameResult.LOSS, 6),
    ;

    private final BettingRate bettingRate;
    private final GameResult gameResult;
    private final int policyOrder;

    BettingResult(BettingRate bettingRate, GameResult gameResult, int policyOrder) {
        this.bettingRate = bettingRate;
        this.gameResult = gameResult;
        this.policyOrder = policyOrder;
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
