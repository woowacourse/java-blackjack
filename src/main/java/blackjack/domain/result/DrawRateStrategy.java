package blackjack.domain.result;

import blackjack.domain.score.Score;

class DrawRateStrategy implements MoneyRateStrategy {

    private static final double DRAW_RATE = 0D;

    @Override
    public double getRate(Score score) {
        return DRAW_RATE;
    }

}
