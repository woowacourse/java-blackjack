package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public final class Stand extends Finish {

    Stand(final Cards cards, final Score score) {
        super(cards, score);
    }

    @Override
    double earningRate(final BlackjackGameState blackjackGameState) {
        if (blackjackGameState.isBust()) {
            return 1;
        }
        if (blackjackGameState.isBlackjack()) {
            return -1;
        }
        return Integer.compare(score(), blackjackGameState.score());
    }
}
