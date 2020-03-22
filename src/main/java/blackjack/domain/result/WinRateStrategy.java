package blackjack.domain.result;

import blackjack.domain.score.Score;

class WinRateStrategy implements MoneyRateStrategy {

    private static final double DEFAULT_WIN_RATE = 1D;
    private static final double BLACKJACK_RATE = 1.5;

    @Override
    public double getRate(Score score) {
        if (score.isBlackjack()) {
            return BLACKJACK_RATE;
        }
        return DEFAULT_WIN_RATE;
    }
}
