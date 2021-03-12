package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Stay extends Finished {
    public Stay(final Cards cards){
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

    @Override
    public boolean isStay() {
        return true;
    }

    @Override
    public boolean isHit() {
        return false;
    }
}
