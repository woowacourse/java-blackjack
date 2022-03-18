package blackjack.domain.state;

import blackjack.domain.card.Cards;

public final class Blackjack extends Finished {

    Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
