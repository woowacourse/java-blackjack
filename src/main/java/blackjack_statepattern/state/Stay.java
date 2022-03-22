package blackjack_statepattern.state;

import blackjack_statepattern.card.Cards;

public final class Stay extends Finished {
    Stay(final Cards cards) {
        super(cards);
    }

    @Override
    protected double computeEarningRate(State comparedState) {
        Cards comparedCards = comparedState.cards();
        if (comparedCards.isBust() || cards().computeScore() > comparedCards.computeScore()) {
            return WIN_RATE;
        }
        if (comparedCards.isBlackjack() || comparedCards.computeScore() > cards().computeScore()) {
            return LOSE_RATE;
        }
        return DRAW_RATE;
    }
}
