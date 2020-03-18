package blackjack.domain.result;

import blackjack.domain.score.Score;

class DrawRate implements MoneyRate {

    private static final double DRAW_RATE = 0D;

    @Override
    public double getRate(Score score) {
        return DRAW_RATE;
    }

}
