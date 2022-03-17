package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public final class Stand extends Finish {

    private static final double STAND_WIN_RATE = 1;
    private static final double STAND_DRAW_RATE = 0;
    private static final double STAND_LOSE_RATE = -1;

    Stand(final Cards cards, final Score score) {
        super(cards, score);
    }

    @Override
    final double earningRate(final BlackjackGameState blackjackGameState) {
        if (blackjackGameState.isBust()) {
            return STAND_WIN_RATE;
        }
        if (blackjackGameState.isBlackjack()) {
            return STAND_LOSE_RATE;
        }
        return earningRateWithStand(blackjackGameState);
    }

    private double earningRateWithStand(final BlackjackGameState blackjackGameState) {
        if (score() > blackjackGameState.score()) {
            return STAND_WIN_RATE;
        }
        if (score() < blackjackGameState.score()) {
            return STAND_LOSE_RATE;
        }
        return STAND_DRAW_RATE;
    }
}
