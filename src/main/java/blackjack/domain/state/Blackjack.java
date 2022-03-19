package blackjack.domain.state;

import blackjack.domain.card.PlayerCards;

public abstract class Blackjack extends Finished {

    Blackjack(PlayerCards cards) {
        this.cards = cards;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }
}
