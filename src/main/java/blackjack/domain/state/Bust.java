package blackjack.domain.state;

import blackjack.domain.card.PlayerCards;

public class Bust extends Finished {

    Bust(PlayerCards cards) {
        this.cards = cards;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }
}
