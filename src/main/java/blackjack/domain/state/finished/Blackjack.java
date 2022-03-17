package blackjack.domain.state.finished;

import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackjackGameState;

public final class Blackjack extends Finished {

    private static final double BLACKJACK_WIN_RATE = 1.5;
    private static final double BLACKJACK_DRAW_RATE = 0;

    public Blackjack(final Cards cards) {
        super(cards, cards.score());
    }

    @Override
    double earningRate(final BlackjackGameState blackjackGameState) {
        if (blackjackGameState.isBlackjack()) {
            return BLACKJACK_DRAW_RATE;
        }
        return BLACKJACK_WIN_RATE;
    }
}
