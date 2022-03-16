package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Blackjack extends Finish {

    public static final int BLACKJACK_TARGET_NUMBER = 21;

    Blackjack(final Cards cards) {
        super(cards, BLACKJACK_TARGET_NUMBER);
    }

    @Override
    double earningRate(final BlackjackGameState blackjackGameState) {
        if (blackjackGameState.isBlackjack()) {
            return 0;
        }
        return 1.5;
    }
}
