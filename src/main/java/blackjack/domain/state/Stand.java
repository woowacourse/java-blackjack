package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stand extends Finished {

    Stand(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
