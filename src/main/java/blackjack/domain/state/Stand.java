package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stand extends Finish {

    Stand(final Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate(final BlackjackGameState blackjackGameState) {
        if (blackjackGameState.isBust()) {
            return 1;
        }
        if (blackjackGameState.isBlackjack()) {
            return 0;
        }
        return earningRateWithStand(blackjackGameState);
    }

    private double earningRateWithStand(final BlackjackGameState blackjackGameState) {
        if (cards.score() == blackjackGameState.score()) {
            return 0;
        }
        return 100;
    }
}
