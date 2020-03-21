package blackjack.domain.result;

import blackjack.domain.score.Score;

class LoseRateStrategy implements MoneyRateStrategy {

    private static final double LOSE_RATE = -1D;

    @Override
    public double getRate(Score score) {
        return LOSE_RATE;
    }

}
