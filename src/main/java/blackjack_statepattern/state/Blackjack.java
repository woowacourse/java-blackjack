package blackjack_statepattern.state;

import blackjack_statepattern.card.Cards;

public final class Blackjack extends Finished {

    Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    protected double earningRate(State comparedState) {
        if (comparedState.cards().isBlackjack()) {
            return DRAW_RATE;
        }
        return BLACKJACK_WIN_RATE;
    }
}
