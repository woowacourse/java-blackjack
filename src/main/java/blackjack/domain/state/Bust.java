package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Bust extends Finished {
    public Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isStay() {
        return false;
    }

    @Override
    public boolean isHit() {
        return false;
    }
}
