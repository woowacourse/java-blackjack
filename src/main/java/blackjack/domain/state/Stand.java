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
        return 0;
    }
}
