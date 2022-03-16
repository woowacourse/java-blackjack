package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stand extends Finish {

    Stand(final Cards cards) {
        super(cards);
    }

    @Override
    double earningRate(final BlackjackGameState blackjackGameState) {
        if (blackjackGameState.isBust()) {
            return 1;
        }
        if (blackjackGameState.isBlackjack()) {
            return 0;
        }
        return Integer.compare(score(), blackjackGameState.score());
    }
}
