package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Blackjack extends Finish {

    Blackjack(final Cards cards) {
        super(cards, cards.createBlackjackScore());
    }

    @Override
    double earningRate(final BlackjackGameState blackjackGameState) {
        if (blackjackGameState.isBlackjack()) {
            return 0;
        }
        return 1.5;
    }
}
