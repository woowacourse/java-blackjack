package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Blackjack extends Finish {

    private static final double BLACKJACK_WIN_RATE = 1.5;
    private static final double BLACKJACK_DRAW_RATE = 0;

    Blackjack(final Cards cards) {
        super(cards, cards.createBlackjackScore());
    }

    @Override
    final double earningRate(final BlackjackGameState blackjackGameState) {
        if (blackjackGameState.isBlackjack()) {
            return BLACKJACK_DRAW_RATE;
        }
        return BLACKJACK_WIN_RATE;
    }
}
