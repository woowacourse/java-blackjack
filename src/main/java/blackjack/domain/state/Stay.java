package blackjack.domain.state;

import blackjack.domain.card.PlayerCards;

public class Stay extends Finished {

    Stay(PlayerCards cards) {
        this.cards = cards;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }
}
