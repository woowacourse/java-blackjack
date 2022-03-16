package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Blackjack extends Finish {

    Blackjack(final Cards cards) {
        super(cards);
    }

    @Override
    double earningRate(final BlackjackGameState blackjackGameState) {
        if (blackjackGameState.isBlackjack()) {
            return 0;
        }
        return 1.5;
    }
}
